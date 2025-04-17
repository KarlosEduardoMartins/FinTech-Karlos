package POO.TI22.FinTechKarlos.model;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class ContaBancaria {
    protected Cliente titular;
    protected double saldo;
    public static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    public ContaBancaria(Cliente titular, double saldo) {
        if (titular == null) {
            throw new IllegalArgumentException("Titular não pode ser nulo.");
        }
        this.titular = titular;
        this.saldo = saldo;
    }

    public String sacar(double valor) {
        if (valor <= 0) {
             return "Valor de saque deve ser positivo.";
        }
        if (saldo >= valor) {
            saldo -= valor;
            return "Você sacou " + currencyFormat.format(valor) + " da conta de " + titular.getNome() + ". Saldo atual: " + currencyFormat.format(saldo);
        } else {
            return "Saldo insuficiente para saque. Saldo atual: " + currencyFormat.format(saldo);
        }
    }

    public String depositar(double valor) {
         if (valor <= 0) {
             return "Valor de depósito deve ser positivo.";
        }
        saldo += valor;
        return "Você depositou " + currencyFormat.format(valor) + " na conta de " + titular.getNome() + ". Saldo atual: " + currencyFormat.format(saldo);
    }

    public String informacoes() {
        return "Titular: " + titular.getNome() + ", CPF: " + titular.getCpf() + ", Saldo: " + currencyFormat.format(saldo);
    }
    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public final void processarAtualizacaoMensal() {
        System.out.println("\n--- Processando Atualização Mensal para " + titular.getNome() + " (" + this.getClass().getSimpleName() + ") ---");
        double saldoAntes = this.saldo;

        double taxas = calcularTaxas();
        if (taxas > 0) {
            this.saldo -= taxas;
            System.out.println("Taxas aplicadas: " + currencyFormat.format(taxas));
        } else {
             System.out.println("Sem taxas a aplicar.");
        }

        aplicarRendimentos();

        gerarLogAtualizacao(saldoAntes);
         System.out.println("----------------------------------------------------------");
    }

    protected abstract double calcularTaxas();

    protected abstract void aplicarRendimentos();

    protected void gerarLogAtualizacao(double saldoAnterior) {
         System.out.println("Saldo anterior: " + currencyFormat.format(saldoAnterior));
         System.out.println("Saldo final após atualização: " + currencyFormat.format(this.saldo));
    }
}