package POO.TI22.FinTechKarlos.service;

import POO.TI22.FinTechKarlos.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    public Cliente criarCliente(Cliente cliente) {
        Cliente novoCliente = new Cliente(cliente.getNome(), cliente.getCpf());
        return novoCliente;
    }
}