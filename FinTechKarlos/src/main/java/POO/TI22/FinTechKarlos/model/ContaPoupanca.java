package POO.TI22.FinTechKarlos.model;

public class ContaPoupanca extends ContaBancaria {
    private static final double TAXA_RENDIMENTO_MENSAL = 0.02; // 2% ao mês

    public ContaPoupanca(Cliente titular, double saldo) {
        super(titular, saldo);
    }

    @Override
    public String informacoes() {
        return "Conta Poupança: " + super.informacoes();
    }

    @Override
    protected double calcularTaxas() {
        System.out.println("Conta Poupança isenta de taxas de manutenção.");
        return 0.0;
    }

    @Override
    protected void aplicarRendimentos() {
        double rendimento = this.saldo * TAXA_RENDIMENTO_MENSAL;
        if (rendimento > 0) {
            this.saldo += rendimento;
             System.out.println("Rendimento de " + currencyFormat.format(rendimento) + " aplicado.");
        } else {
             System.out.println("Saldo insuficiente para gerar rendimentos significativos.");
        }
    }
}