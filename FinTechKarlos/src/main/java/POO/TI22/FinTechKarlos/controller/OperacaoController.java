package POO.TI22.FinTechKarlos.controller;

import POO.TI22.FinTechKarlos.facade.FintechFacade;
import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.model.ContaBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/operacoes")
public class OperacaoController {

    private final FintechFacade fintechFacade;

    @Autowired
    public OperacaoController(FintechFacade fintechFacade) {
        this.fintechFacade = fintechFacade;
    }

    public static class AbrirContaRequest {
        @Valid
        public Cliente cliente;
        public String tipoConta;
        public double saldoInicial;
    }

    @PostMapping("/abrir-conta")
    public ResponseEntity<?> abrirConta(@Valid @RequestBody AbrirContaRequest request) {
        try {
            ContaBancaria contaCriada = fintechFacade.abrirContaCliente(
                request.cliente,
                request.tipoConta,
                request.saldoInicial
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(
                "Conta criada com sucesso! " + contaCriada.informacoes()
            );
        } catch (IllegalArgumentException | IllegalStateException e) {
             System.err.println("Erro ao tentar abrir conta via Facade: " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao abrir conta: " + e.getMessage());
        } catch (Exception e) {
             System.err.println("Erro inesperado ao tentar abrir conta via Facade: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor: " + e.getMessage());
        }
    }

     @PostMapping("/processamento-mensal")
     public ResponseEntity<?> executarProcessamentoMensal() {
         try {
             fintechFacade.executarProcessamentoMensalContas();
             return ResponseEntity.ok("Processamento mensal executado com sucesso para todas as contas.");
         } catch (Exception e) {
              System.err.println("Erro ao executar processamento mensal via Facade: " + e.getMessage());
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Erro interno ao executar processamento mensal: " + e.getMessage());
         }
     }
}