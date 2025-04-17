package POO.TI22.FinTechKarlos.service;

import POO.TI22.FinTechKarlos.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClienteService {
    private final Map<String, Cliente> clientesByCpf = new ConcurrentHashMap<>();

    public Cliente criarOuObterCliente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null || cliente.getNome() == null) {
             throw new IllegalArgumentException("Dados do cliente inválidos (nulos).");
        }

        Cliente existente = clientesByCpf.get(cliente.getCpf());
        if (existente != null) {
            if (!existente.getNome().equalsIgnoreCase(cliente.getNome())) {
                throw new IllegalArgumentException("CPF " + cliente.getCpf() + " já cadastrado para outro nome: " + existente.getNome());
            }
            System.out.println("Cliente com CPF " + cliente.getCpf() + " já existe. Retornando cliente existente.");
            return existente;
        } else {
            Cliente novoCliente = new Cliente(cliente.getNome(), cliente.getCpf());
            clientesByCpf.put(novoCliente.getCpf(), novoCliente);
            System.out.println("\nCliente criado: " + novoCliente.getNome() + ", CPF: " + novoCliente.getCpf() + ".");
            return novoCliente;
        }
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return Optional.ofNullable(clientesByCpf.get(cpf));
    }
}