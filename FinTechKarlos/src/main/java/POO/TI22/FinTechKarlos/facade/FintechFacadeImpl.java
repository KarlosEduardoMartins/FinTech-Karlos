package POO.TI22.FinTechKarlos.facade;

import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.ContaBancaria;
import POO.TI22.FinTechKarlos.service.ClienteService;
import POO.TI22.FinTechKarlos.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class FintechFacadeImpl implements FintechFacade {

    private final ClienteService clienteService;
    private final ContaService contaService;

    @Autowired
    public FintechFacadeImpl(ClienteService clienteService, ContaService contaService) {
        this.clienteService = clienteService;
        this.contaService = contaService;
    }

    @Override
    public ContaBancaria abrirContaCliente(Cliente dadosCliente, String tipoConta, double saldoInicial) {
         System.out.println("\n--- Operação Facade: Abrir Conta Cliente ---");

         Cliente cliente = clienteService.criarOuObterCliente(dadosCliente);


         ContaBancaria novaConta;
         if ("corrente".equalsIgnoreCase(tipoConta)) {
             novaConta = contaService.criarContaCorrente(cliente, saldoInicial);
         } else if ("poupanca".equalsIgnoreCase(tipoConta) || "poupança".equalsIgnoreCase(tipoConta)) {
             novaConta = contaService.criarContaPoupanca(cliente, saldoInicial);
         } else {
             throw new IllegalArgumentException("Tipo de conta inválido: " + tipoConta + ". Use 'corrente' ou 'poupanca'.");
         }
          System.out.println("--- Fim Operação Facade: Conta aberta com sucesso ---");
         return novaConta;
    }

    @Override
    public void executarProcessamentoMensalContas() {
         System.out.println("\n--- Operação Facade: Executar Processamento Mensal ---");
         contaService.processarAtualizacaoMensalTodasContas();
         System.out.println("--- Fim Operação Facade: Processamento Mensal Concluído ---");
    }
}