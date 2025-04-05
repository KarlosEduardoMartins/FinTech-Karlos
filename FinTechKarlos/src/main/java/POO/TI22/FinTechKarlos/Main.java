package POO.TI22.FinTechKarlos;

import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.ContaCorrente;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João Silva", "123.456.789-00");
        ContaCorrente contaCorrente = new ContaCorrente(cliente, 1000.0);

        contaCorrente.sacar(150.0);
    }
}