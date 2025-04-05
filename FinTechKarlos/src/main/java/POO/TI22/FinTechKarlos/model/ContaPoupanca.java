package POO.TI22.FinTechKarlos.model;

public class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(Cliente titular, double saldo) {
        super(titular, saldo);
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    public void renderJuros() {
        saldo *= 1.02; // 2% de juros ao mês
    }
}
