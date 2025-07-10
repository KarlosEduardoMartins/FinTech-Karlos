package POO.TI22.FinTechKarlos.model;

import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends ContaBancaria {

    private double limiteChequeEspecial;

    public ContaCorrente() {
        super(null, 0.0);
        this.limiteChequeEspecial = 0.0;
    }

    public ContaCorrente(Cliente cliente, double saldo) {
        super(cliente, saldo);
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
        return "Conta Corrente: " + super.informacoes() + ", Limite: " + limiteChequeEspecial;
    }
}