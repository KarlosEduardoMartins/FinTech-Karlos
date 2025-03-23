package POO.TI22.FinTechKarlos;

import POO.TI22.FinTechKarlos.model.Agencia;
import POO.TI22.FinTechKarlos.model.Cliente;

public class Main {
    public static void main(String[] args) {
        Agencia agencia1 = new Agencia(1);
        Cliente cliente1 = new Cliente("Fernando", 22, "032.305.025-55");
        Cliente cliente2 = new Cliente("João", 25, "032.305.025-56");
        System.out.println(cliente1.toString());

        agencia1.AdicionarCliente(cliente1);
        agencia1.AdicionarCliente(cliente2);
        System.out.println(agencia1.toString());
    }
}