package POO.TI22.FinTechKarlos.model;

public abstract class ContaBancaria {
    protected Cliente titular;
    protected double saldo;

    public ContaBancaria(Cliente titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String sacar(double valor) {
        saldo -= valor;
        return "Você sacou R$ " + valor + " da conta poupança de " + titular.getNome() + ". Saldo atual: R$ " + saldo;
    }
   
    public String depositar(double valor) {
        saldo += valor;
        return "Você depositou R$ " + valor + " na conta poupança de " + titular.getNome() + ". Saldo atual: R$ " + saldo;
    }

    public String informacoes() {
        return "Titular: " + titular.getNome() + ", CPF: " + titular.getCpf() + ", Saldo: R$ " + saldo;
    }
}
