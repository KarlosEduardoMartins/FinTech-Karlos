package POO.TI22.FinTechKarlos.model;

public class ContaCorrente extends ContaBancaria {
    private String titular;
    private double limiteChequeEspecial;

    public ContaCorrente() {
        super(null, 0.0);
        this.titular = "";
        this.limiteChequeEspecial = 0.0;
    }

    public ContaCorrente(Cliente cliente, double saldo) {
        super(cliente, saldo);
        this.titular = cliente.getNome();
        this.limiteChequeEspecial = 500.0;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public String informacoes() {
        return "Conta Corrente: " + super.informacoes() + ", Titular: " + titular + ", Limite: " + limiteChequeEspecial;
    }
}
