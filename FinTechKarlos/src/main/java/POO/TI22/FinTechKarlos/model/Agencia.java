package POO.TI22.FinTechKarlos.model;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
    int numero;
    List<Cliente> clientes;

    public Agencia(int numero) {
        this.numero = numero;
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }
}