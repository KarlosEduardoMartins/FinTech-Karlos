package POO.TI22.FinTechKarlos.model;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(Cliente titular, double saldo) {
        super(titular, saldo);
    }

    public ContaPoupanca() {
        super();
    }

    public void renderJuros() {
        saldo *= 1.02;
    }

    @Override
    public String informacoes() {
        return "Conta Poupança: " + super.informacoes();
    }
}
