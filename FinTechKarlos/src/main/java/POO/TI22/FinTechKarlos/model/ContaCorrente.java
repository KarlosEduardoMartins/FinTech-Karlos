package POO.TI22.FinTechKarlos.model;

public class ContaCorrente extends ContaBancaria {
    private String titular;

    public ContaCorrente(Cliente cliente, double saldo) {
        super(cliente, saldo);
        this.titular = cliente.getNome();
    }

    @Override
    public String informacoes() {
        return "Conta Corrente: " + super.informacoes() + ", Titular: " + titular;
    }
    
}
