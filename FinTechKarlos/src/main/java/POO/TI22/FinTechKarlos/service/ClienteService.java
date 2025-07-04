package POO.TI22.FinTechKarlos.service;

import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.ContaBancaria;
import POO.TI22.FinTechKarlos.model.ContaCorrente;
import POO.TI22.FinTechKarlos.model.ContaPoupanca;
import POO.TI22.FinTechKarlos.repository.ClienteRepository;
import POO.TI22.FinTechKarlos.repository.ContaCorrenteRepository;
import POO.TI22.FinTechKarlos.repository.ContaPoupancaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    // 1. Instanciando o logger para esta classe
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;
    private final ContaCorrenteRepository contaCorrenteRepository;
    private final ContaPoupancaRepository contaPoupancaRepository;

    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        logger.info("Iniciando a criação de um novo cliente com CPF: {}", cliente.getCpf());

        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExistente.isPresent()) {
            logger.error("ERRO: Tentativa de criar cliente com CPF que já existe: {}", cliente.getCpf());
            throw new IllegalStateException("Já existe um cliente cadastrado com este CPF.");
        }

        Cliente novoCliente = clienteRepository.save(cliente);
        logger.info("Cliente {} criado com sucesso com o ID: {}", novoCliente.getNome(), novoCliente.getId());
        return novoCliente;
    }

    public List<Cliente> listarTodos() {
        logger.debug("Buscando a lista completa de todos os clientes.");
        return clienteRepository.findAll();
    }

    public Cliente buscarPorCpf(String cpf) {
        logger.debug("Buscando cliente pelo CPF: {}", cpf);
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    logger.warn("AVISO: Cliente não encontrado para o CPF: {}", cpf);
                    return new RuntimeException("Cliente não encontrado para o CPF: " + cpf);
                });
    }

    @Transactional
    public Cliente atualizarCliente(String cpf, Cliente clienteAtualizado) {
        logger.info("Iniciando atualização do cliente com CPF: {}", cpf);
        Cliente clienteExistente = buscarPorCpf(cpf);
        clienteExistente.setNome(clienteAtualizado.getNome());

        Cliente clienteSalvo = clienteRepository.save(clienteExistente);
        logger.info("Cliente com CPF {} atualizado com sucesso.", cpf);
        return clienteSalvo;
    }

    @Transactional
    public void deletarCliente(String cpf) {
        logger.info("Iniciando exclusão do cliente com CPF: {}", cpf);
        Cliente cliente = buscarPorCpf(cpf);
        clienteRepository.delete(cliente);
        logger.info("Cliente {} com CPF {} excluído com sucesso.", cliente.getNome(), cpf);
    }

    @Transactional
    public ContaCorrente criarContaCorrente(String clienteCpf, Double saldoInicial) {
        Cliente cliente = buscarPorCpf(clienteCpf);
        ContaCorrente contaCorrente = new ContaCorrente(cliente, saldoInicial);
        return contaCorrenteRepository.save(contaCorrente);
    }

    @Transactional
    public ContaPoupanca criarContaPoupanca(String clienteCpf, Double saldoInicial) {
        Cliente cliente = buscarPorCpf(clienteCpf);
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente, saldoInicial);
        return contaPoupancaRepository.save(contaPoupanca);
    }

    public ContaBancaria buscarContaPorCpfETipo(String cpf, String tipoConta) {
        if ("corrente".equalsIgnoreCase(tipoConta)) {
            return contaCorrenteRepository.findByTitular_Cpf(cpf)
                    .orElseThrow(() -> new RuntimeException("Conta corrente não encontrada para o CPF: " + cpf));
        } else if ("poupanca".equalsIgnoreCase(tipoConta)) {
            return contaPoupancaRepository.findByTitular_Cpf(cpf)
                    .orElseThrow(() -> new RuntimeException("Conta poupança não encontrada para o CPF: " + cpf));
        }
        throw new IllegalArgumentException("Tipo de conta inválido: " + tipoConta);
    }

    @Transactional
    public void salvarConta(ContaBancaria conta) {
        if (conta instanceof ContaCorrente) {
            contaCorrenteRepository.save((ContaCorrente) conta);
        } else if (conta instanceof ContaPoupanca) {
            contaPoupancaRepository.save((ContaPoupanca) conta);
        }
    }

    @Transactional
    public void deletarContaPorCpfETipo(String cpf, String tipoConta) {
        ContaBancaria conta = buscarContaPorCpfETipo(cpf, tipoConta);
        if ("corrente".equalsIgnoreCase(tipoConta)) {
            contaCorrenteRepository.delete((ContaCorrente) conta);
        } else if ("poupanca".equalsIgnoreCase(tipoConta)) {
            contaPoupancaRepository.delete((ContaPoupanca) conta);
        }
    }

    @Transactional
    public ContaCorrente atualizarContaCorrente(String cpf, ContaCorrente dadosAtualizacao) {
        logger.info("Iniciando atualização da conta corrente do cliente com CPF: {}", cpf);
        ContaCorrente contaExistente = (ContaCorrente) buscarContaPorCpfETipo(cpf, "corrente");

        contaExistente.setLimiteChequeEspecial(dadosAtualizacao.getLimiteChequeEspecial());

        ContaCorrente contaSalva = contaCorrenteRepository.save(contaExistente);
        logger.info("Conta corrente do CPF {} atualizada com sucesso.", cpf);
        return contaSalva;
    }

    @Transactional
    public ContaPoupanca atualizarContaPoupanca(String cpf, ContaPoupanca dadosAtualizacao) {
        logger.info("Iniciando atualização da conta poupança do cliente com CPF: {}", cpf);
        ContaPoupanca contaExistente = (ContaPoupanca) buscarContaPorCpfETipo(cpf, "poupanca");

        contaExistente.setSaldo(dadosAtualizacao.getSaldo());

        ContaPoupanca contaSalva = contaPoupancaRepository.save(contaExistente);
        logger.info("Conta poupança do CPF {} atualizada com sucesso.", cpf);
        return contaSalva;
    }
}