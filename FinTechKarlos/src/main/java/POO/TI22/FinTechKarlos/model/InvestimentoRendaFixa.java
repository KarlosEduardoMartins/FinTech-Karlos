package POO.TI22.FinTechKarlos.model;

public class InvestimentoRendaFixa implements Investimento {
    private double valor;
   
    public InvestimentoRendaFixa(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularRetorno() {
        return valor * 1.05; // 5% de retorno fixo
    }
}
