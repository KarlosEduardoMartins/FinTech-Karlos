package POO.TI22.FinTechKarlos.facade;

import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.ContaBancaria;

public interface FintechFacade {

    ContaBancaria abrirContaCliente(Cliente dadosCliente, String tipoConta, double saldoInicial);

    void executarProcessamentoMensalContas();
}