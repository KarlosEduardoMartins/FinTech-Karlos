package POO.TI22.FinTechKarlos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinTechKarlosApplication {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando a aplicação FinTech Karlos...");
            System.out.println("Aguarde, isso pode levar alguns segundos...");
            SpringApplication.run(FinTechKarlosApplication.class, args);
            System.out.println("Aplicação iniciada com sucesso!");
            System.out.println("Acesse http://localhost:8080 para interagir com a API.");
        } catch (Exception e) {
            System.err.println("Erro ao iniciar a aplicação: " + e.getMessage());
        }
    }
}
