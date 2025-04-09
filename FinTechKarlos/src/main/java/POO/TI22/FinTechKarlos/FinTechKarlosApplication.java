package POO.TI22.FinTechKarlos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinTechKarlosApplication {
    public static final String ANSI_RESET = "\u001b[0m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando a aplicação FinTech Karlos...");
            System.out.println("Aguarde, isso pode levar alguns segundos...\n\n\n");
            SpringApplication.run(FinTechKarlosApplication.class, args);
            System.out.println("Aplicação iniciada com sucesso!");
            System.out.println("Acesse " + ANSI_CYAN + "http://localhost:8080" + ANSI_RESET + " para interagir com a API." + "\n\n\n");
        } catch (Exception e) {
            System.err.println("Erro ao iniciar a aplicação: " + e.getMessage());
        }
    }
}
