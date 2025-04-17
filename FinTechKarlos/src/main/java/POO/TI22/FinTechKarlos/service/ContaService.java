package POO.TI22.FinTechKarlos.service;

import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.ContaBancaria;
import POO.TI22.FinTechKarlos.model.ContaCorrente;
import POO.TI22.FinTechKarlos.model.ContaPoupanca;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ContaService {
    private final Map<String, ContaBancaria> contasByCpfTitular = new ConcurrentHashMap<>();

    public ContaBancaria criarContaCorrente(Cliente titular, double saldoInicial) {
        if (contasByCpfTitular.containsKey(titular.getCpf())) {
            throw new IllegalStateException("Cliente já possui uma conta cadastrada.");
        }
        ContaCorrente novaConta = new ContaCorrente(titular, saldoInicial);
        contasByCpfTitular.put(titular.getCpf(), novaConta);
        System.out.println("Conta Corrente criada para " + titular.getNome() + " com saldo inicial de " + ContaBancaria.currencyFormat.format(saldoInicial));
        return novaConta;
    }

    public ContaBancaria criarContaPoupanca(Cliente titular, double saldoInicial) {
         if (contasByCpfTitular.containsKey(titular.getCpf())) {
            throw new IllegalStateException("Cliente já possui uma conta cadastrada.");
        }
        ContaPoupanca novaConta = new ContaPoupanca(titular, saldoInicial);
        contasByCpfTitular.put(titular.getCpf(), novaConta);
        System.out.println("Conta Poupança criada para " + titular.getNome() + " com saldo inicial de " + ContaBancaria.currencyFormat.format(saldoInicial));
        return novaConta;
    }

    public Optional<ContaBancaria> buscarContaPorCpfTitular(String cpf) {
        return Optional.ofNullable(contasByCpfTitular.get(cpf));
    }

    public void processarAtualizacaoMensalTodasContas() {
         System.out.println("\n===== INICIANDO PROCESSAMENTO MENSAL DE CONTAS =====");
         if (contasByCpfTitular.isEmpty()) {
              System.out.println("Nenhuma conta para processar.");
         } else {
            contasByCpfTitular.values().forEach(ContaBancaria::processarAtualizacaoMensal);
         }
         System.out.println("===== FIM DO PROCESSAMENTO MENSAL DE CONTAS =====\n");
    }
}