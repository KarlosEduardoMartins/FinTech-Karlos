package POO.TI22.FinTechKarlos.factory;

import POO.TI22.FinTechKarlos.model.Investimento;
import POO.TI22.FinTechKarlos.model.InvestimentoRendaFixa;

public class InvestimentoFactory {

    public static Investimento createInvestimento(String tipo, double valor) {
        if ("RENDA_FIXA".equalsIgnoreCase(tipo)) {
            return new InvestimentoRendaFixa(valor);
        }

        throw new IllegalArgumentException("Tipo de investimento desconhecido: " + tipo);
    }
}