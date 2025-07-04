package POO.TI22.FinTechKarlos.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    protected Cliente titular;

    @Column(nullable = false)
    protected double saldo;

    public ContaBancaria() {
    }

    public ContaBancaria(Cliente titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            return "Você sacou R$ " + valor + " da conta de " + titular.getNome() + ". Saldo atual: R$ " + this.saldo;
        }
        return "Saque não realizado. Saldo insuficiente ou valor inválido.";
    }

    public String depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return "Você depositou R$ " + valor + " na conta de " + titular.getNome() + ". Saldo atual: R$ "
                    + this.saldo;
        }
        return "Depósito não realizado. Valor inválido.";
    }

    public String informacoes() {
        return "Titular: " + titular.getNome() + ", CPF: " + titular.getCpf() + ", Saldo: R$ " + saldo;
    }
}