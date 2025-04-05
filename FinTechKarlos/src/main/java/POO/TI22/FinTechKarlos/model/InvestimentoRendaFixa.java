package POO.TI22.FinTechKarlos.model;

public class InvestimentoRendaFixa implements Investimento {
    private double valor;
   
    public InvestimentoRendaFixa(double valor) {
        this.valor = valor;
    }

    @Override
    public String calcularRetorno() {
        valor *= 1.05;
        return "Retorno do investimento em renda fixa: R$ " + valor;
    }
}
