package POO.TI22.FinTechKarlos.controller;

import POO.TI22.FinTechKarlos.model.ContaPoupanca;
import POO.TI22.FinTechKarlos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/contas-poupanca")
@RequiredArgsConstructor
public class ContaPoupancaController {

    private final ClienteService clienteService;

    @PostMapping("/criar/{clienteCpf}")
    @Transactional
    public ResponseEntity<ContaPoupanca> criarContaPoupanca(
            @PathVariable String clienteCpf,
            @RequestParam Double saldoInicial) {
        try {
            ContaPoupanca novaConta = clienteService.criarContaPoupanca(clienteCpf, saldoInicial);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ContaPoupanca> buscarContaPoupanca(@PathVariable String cpf) {
        try {
            ContaPoupanca conta = (ContaPoupanca) clienteService.buscarContaPorCpfETipo(cpf, "poupanca");
            return ResponseEntity.ok(conta);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/render-juros")
    @Transactional
    public ResponseEntity<String> renderJuros(@RequestParam String cpf) {
        try {
            ContaPoupanca conta = (ContaPoupanca) clienteService.buscarContaPorCpfETipo(cpf, "poupanca");
            conta.renderJuros();
            clienteService.salvarConta(conta);

            String mensagem = "Juros rendidos com sucesso! Novo saldo: R$ " + String.format("%.2f", conta.getSaldo());
            System.out.println("\n" + mensagem);
            return ResponseEntity.ok(mensagem);

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ContaPoupanca> atualizarContaPoupanca(
            @PathVariable String cpf,
            @RequestBody ContaPoupanca dadosConta) {
        try {
            ContaPoupanca contaAtualizada = clienteService.atualizarContaPoupanca(cpf, dadosConta);
            return ResponseEntity.ok(contaAtualizada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarContaPoupanca(@PathVariable String cpf) {
        try {
            clienteService.deletarContaPorCpfETipo(cpf, "poupanca");
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
