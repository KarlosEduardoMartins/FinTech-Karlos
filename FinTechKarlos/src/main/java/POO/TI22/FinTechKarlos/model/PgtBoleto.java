package POO.TI22.FinTechKarlos.model;

public class PgtBoleto extends Pagamento {
    public PgtBoleto(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento por boleto processado: R$" + valor);
    }
}
