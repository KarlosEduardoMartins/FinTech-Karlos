package POO.TI22.FinTechKarlos.model;

public class PgtPix extends Pagamento {
    public PgtPix(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento via Pix processado: R$" + valor);
    }
}