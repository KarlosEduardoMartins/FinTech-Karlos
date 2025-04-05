package POO.TI22.FinTechKarlos.model;

public class ContaCorrente extends ContaBancaria {
    private String titular;

    public ContaCorrente(Cliente cliente, double saldo) {
        super(cliente, saldo);
        this.titular = cliente.getNome();
    }

    @Override
    public String sacar(double valor) {
        saldo -= (valor + 5);
        return "Conta corrente de " + titular + ": Saque de R$" + valor + " realizado. Saldo atual: R$" + saldo;
    }
}
