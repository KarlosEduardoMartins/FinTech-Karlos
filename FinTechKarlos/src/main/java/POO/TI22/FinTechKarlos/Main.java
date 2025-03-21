package POO.TI22.FinTechKarlos;

import POO.TI22.FinTechKarlos.model.Agencia;
import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.Conta;
import POO.TI22.FinTechKarlos.model.Pagamento;
import POO.TI22.FinTechKarlos.model.Transacao;
import POO.TI22.FinTechKarlos.model.PgtBoleto;
import POO.TI22.FinTechKarlos.model.PgtPix;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("João da Silva", "123.456.789-00");
        Conta conta1 = new Conta(1234, 1000.0);
        Transacao transacao1 = new Transacao(cliente1, conta1, 100.0, "2023-10-27");

        Agencia agencia1 = new Agencia(1001);
        agencia1.adicionarCliente(cliente1);

        Pagamento pagamento1 = new PgtBoleto(50.0);
        Pagamento pagamento2 = new PgtPix(75.0);

        pagamento1.processarPagamento();
        pagamento2.processarPagamento();
    }
}