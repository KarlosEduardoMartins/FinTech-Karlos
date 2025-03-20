package POO.TI22.FinTechKarlos.model;

import java.util.Scanner;

public class ContaBancaria {
    private static int contadorDeContas = 1;
    public int numeroConta;
    private String titular;
    private double saldo;
    private double credito;
    private double creditoUsado;
    private double creditoEmergencial;

    private static Scanner scanner = new Scanner(System.in);

    public ContaBancaria(String titular, double saldoInicial, double creditoInicial) {
        this.numeroConta = contadorDeContas++;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.credito = creditoInicial;
        this.creditoUsado = 0;
        this.creditoEmergencial = 0;
    }

    public void exibirConta() {
        System.out.println("\nInformações da conta de " + this.titular + ":");
        System.out.println("\nNúmero da conta: " + this.numeroConta +
                "\nTitular: " + this.titular +
                "\nSaldo: " + this.saldo +
                "\nLimite de crédito: " + this.credito +
                "\nCrédito usado: " + this.creditoUsado);

        if (this.creditoEmergencial != 0) {
            System.out.println("Limite de crédito emergencial: " + this.creditoEmergencial);
        }
    }

    public void exibirSaldo() {
        System.out.println("\nSaldo atual de " + this.titular + ": R$ " + this.saldo);
    }

    public void depositar(double valor) {
        this.saldo += valor;
        System.out.println("\nDepósito de R$ " + valor + " realizado por " + this.titular);
    }

    public void sacar(double valor) {
        System.out.println("\nTentativa de saque de R$ " + valor + " por " + this.titular);
        if (this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("\nSaque de R$ " + valor + " realizado por " + this.titular);
        } else {
            System.out.println("\nSaldo insuficiente para saque de R$ " + valor);
        }
    }

    public void transferir(ContaBancaria destino, double valor) {
        System.out.println(
                "\nTentativa de transferência de R$ " + valor + " de " + this.titular + " para " + destino.titular);
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.saldo += valor;
            System.out.println("\nTransferência realizada de " + this.titular + " para " + destino.titular
                    + " no valor de R$ " + valor);
        } else {
            System.out.println("\nSaldo insuficiente para transferência de R$ " + valor);
        }
    }

    public void compraCredito(double valor) {
        System.out.println("\nTentativa de compra de crédito de R$ " + valor + " realizada por " + this.titular);

        if ((this.creditoUsado + valor) < credito) {
            this.creditoUsado += valor;
            System.out.println("\nCompra de crédito de R$ " + valor + " aprovada para " + this.titular);
        } else {
            System.out.print(this.titular + " possui R$ " + this.credito
                    + " de credito. Não é o suficiente para realizar a compra. Deseja utilizar o crédito emergencial? (S/N): ");
            String perguntaCreditoEmergencial = scanner.nextLine();

            if (perguntaCreditoEmergencial.equalsIgnoreCase("S")) {
                this.creditoEmergencial = (this.creditoUsado + valor) - this.credito;
                this.creditoUsado += valor;
                System.out.println("\nCompra de crédito de R$ " + valor + " aprovada usando crédito emergencial para "
                        + this.titular);
            } else {
                System.out.println("\nLimite de crédito insuficiente para compra de R$ " + valor);
            }
        }
    }

    public void pagarCartao(double valor) {
        System.out.println(
                "\nTentativa de pagamento do cartão de crédito de R$ " + valor + " realizada por " + this.titular);

        if (valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("\nPagamento de cartão de R$ " + valor + " realizado");
            if ((this.creditoEmergencial - valor) <= 0) {
                this.creditoEmergencial = 0;
                this.creditoUsado -= valor;
            } else {
                this.creditoEmergencial -= valor;
                this.creditoUsado -= valor;
            }

            if (this.creditoUsado <= 0) {
                this.saldo += (this.creditoUsado * -1);
                System.out.println("\nPagamento excedeu o valor do crédito. Valor de R$ " + (this.creditoUsado * -1)
                        + " foi devolvido para a conta");
                this.creditoUsado = 0;
            }
        } else {
            System.out.println("\nSaldo insuficiente para pagar cartão de R$ " + valor);
        }
    }
}