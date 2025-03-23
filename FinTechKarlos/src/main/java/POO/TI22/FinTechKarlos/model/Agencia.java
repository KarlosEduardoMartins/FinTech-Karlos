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
    
    public void AdicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }
    
    @Override
    public String toString() {
        return "Agencia: \n\nNumero: " + getNumero() + "\nClientes: " + getClientes();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}