package POO.TI22.FinTechKarlos.model;

public class Conta extends Agencia {
    int numero;
    double saldo;
    public Conta(int numero, int numero2, double saldo) {
        super(numero);
        numero = numero2;
        this.saldo = saldo;
    }
}