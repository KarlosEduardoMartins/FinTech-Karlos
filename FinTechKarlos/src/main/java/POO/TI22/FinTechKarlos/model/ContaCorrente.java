package POO.TI22.FinTechKarlos.model;

public class ContaCorrente extends ContaBancaria {
    private static final double TAXA_MANUTENCAO_MENSAL = 15.00;

    public ContaCorrente(Cliente cliente, double saldo) {
        super(cliente, saldo);
    }

    @Override
    public String informacoes() {
        return "Conta Corrente: " + super.informacoes();
    }

    @Override
    protected double calcularTaxas() {
        System.out.println("Calculando taxa de manutenção para Conta Corrente.");
         return TAXA_MANUTENCAO_MENSAL;
    }

    @Override
    protected void aplicarRendimentos() {
         System.out.println("Conta Corrente não possui rendimentos automáticos.");
    }
}