package POO.TI22.FinTechKarlos.controller;

import POO.TI22.FinTechKarlos.model.ContaCorrente;
import POO.TI22.FinTechKarlos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/contas-corrente")
@RequiredArgsConstructor
public class ContaCorrenteController {

    private final ClienteService clienteService;

    @PostMapping("/criar/{clienteCpf}")
    @Transactional
    public ResponseEntity<ContaCorrente> criarContaCorrente(
            @PathVariable String clienteCpf,
            @RequestParam Double saldoInicial) {
        try {
            ContaCorrente novaConta = clienteService.criarContaCorrente(clienteCpf, saldoInicial);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ContaCorrente> buscarContaCorrente(@PathVariable String cpf) {
        try {
            ContaCorrente conta = (ContaCorrente) clienteService.buscarContaPorCpfETipo(cpf, "corrente");
            return ResponseEntity.ok(conta);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/sacar")
    @Transactional
    public ResponseEntity<String> sacar(
            @RequestParam String cpf,
            @RequestParam double valor) {
        try {
            ContaCorrente conta = (ContaCorrente) clienteService.buscarContaPorCpfETipo(cpf, "corrente");
            String resultadoSaque = conta.sacar(valor);

            if (!resultadoSaque.contains("Saldo insuficiente")) {
                clienteService.salvarConta(conta);
            } else {
                throw new IllegalArgumentException(resultadoSaque);
            }

            return ResponseEntity.ok(resultadoSaque);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/depositar")
    @Transactional
    public ResponseEntity<String> depositar(
            @RequestParam String cpf,
            @RequestParam double valor) {
        try {
            ContaCorrente conta = (ContaCorrente) clienteService.buscarContaPorCpfETipo(cpf, "corrente");
            String resultadoDeposito = conta.depositar(valor);

            if (!resultadoDeposito.contains("Valor inválido")) {
                clienteService.salvarConta(conta);
            } else {
                throw new IllegalArgumentException(resultadoDeposito);
            }

            return ResponseEntity.ok(resultadoDeposito);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarContaCorrente(@PathVariable String cpf) {
        try {
            clienteService.deletarContaPorCpfETipo(cpf, "corrente");
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ContaCorrente> atualizarContaCorrente(
            @PathVariable String cpf,
            @RequestBody ContaCorrente dadosConta) {
        try {
            ContaCorrente contaAtualizada = clienteService.atualizarContaCorrente(cpf, dadosConta);
            return ResponseEntity.ok(contaAtualizada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
