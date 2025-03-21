package POO.TI22.FinTechKarlos.model;

public abstract class Pagamento {
    double valor;

    public Pagamento(double valor) {
        this.valor = valor;
    }

    public abstract void processarPagamento();
}
