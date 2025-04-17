package POO.TI22.FinTechKarlos;

import POO.TI22.FinTechKarlos.facade.FintechFacade;
import POO.TI22.FinTechKarlos.model.Cliente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext; // Importar

@SpringBootApplication
public class FinTechKarlosApplication {
    public static final String ANSI_RESET = "\u001b[0m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando a aplicação FinTech Karlos...");
            System.out.println("Aguarde, isso pode levar alguns segundos...\n\n\n");
            ConfigurableApplicationContext context = SpringApplication.run(FinTechKarlosApplication.class, args);
            System.out.println("Aplicação iniciada com sucesso!");
            System.out.println("Acesse " + ANSI_CYAN + "http://localhost:8080" + ANSI_RESET + " para interagir com a API." + "\n\n\n");

            FintechFacade facade = context.getBean(FintechFacade.class);

            System.out.println(ANSI_YELLOW + "\n--- Testando Facade e Template Method diretamente ---" + ANSI_RESET);
            try {
                Cliente cliente1 = new Cliente("Maria Silva", "11122233344");
                facade.abrirContaCliente(cliente1, "poupanca", 1000.00);

                Cliente cliente2 = new Cliente("Joao Souza", "55566677788");
                facade.abrirContaCliente(cliente2, "corrente", 500.00);

                 Cliente cliente1Again = new Cliente("Maria Silva", "11122233344");

                 Cliente clienteConflict = new Cliente("Mario Silva", "11122233344");

            } catch (Exception e) {
                System.err.println("Erro no teste de abertura de conta: " + e.getMessage());
            }
            
            facade.executarProcessamentoMensalContas();
             
            System.out.println(ANSI_YELLOW + "--- Fim dos testes diretos ---" + ANSI_RESET);
            System.out.println("\nAplicação continua rodando para receber requisições API...\n");


        } catch (Exception e) {
            System.err.println("Erro CRÍTICO ao iniciar ou testar a aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}