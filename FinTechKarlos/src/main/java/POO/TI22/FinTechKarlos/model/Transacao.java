package POO.TI22.FinTechKarlos.model;

public class Transacao {
    Cliente cliente;
    Conta conta;
    double valor;
    String data;

    public Transacao(Cliente cliente, Conta conta, double valor, String data) {
        this.cliente = cliente;
        this.conta = conta;
        this.valor = valor;
        this.data = data;
    }
}
