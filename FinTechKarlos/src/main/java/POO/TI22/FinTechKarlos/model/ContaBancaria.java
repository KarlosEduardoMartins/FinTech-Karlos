package POO.TI22.FinTechKarlos.model;

public abstract class ContaBancaria {
    protected Cliente titular;
    protected double saldo;

    public ContaBancaria(Cliente titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public abstract void sacar(double valor);
   
    public void depositar(double valor) {
        saldo += valor;
    }
}
