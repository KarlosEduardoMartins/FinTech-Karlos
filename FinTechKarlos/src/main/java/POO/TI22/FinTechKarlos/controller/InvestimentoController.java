package POO.TI22.FinTechKarlos.controller;

import POO.TI22.FinTechKarlos.model.InvestimentoAplicado;
import POO.TI22.FinTechKarlos.service.InvestimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/investimentos")
@RequiredArgsConstructor
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @PostMapping("/simular")
    public ResponseEntity<String> simularInvestimento(
            @RequestParam String tipo,
            @RequestParam double valor) {
        try {
            String retorno = investimentoService.simularInvestimento(tipo, valor);
            return ResponseEntity.ok(retorno);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<InvestimentoAplicado> criarInvestimento(@RequestBody InvestimentoAplicado investimento) {
        InvestimentoAplicado novoInvestimento = investimentoService.criarInvestimento(investimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoInvestimento);
    }

    @GetMapping
    public ResponseEntity<List<InvestimentoAplicado>> listarInvestimentos() {
        return ResponseEntity.ok(investimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoAplicado> buscarInvestimentoPorId(@PathVariable Long id) {
        try {
            InvestimentoAplicado investimento = investimentoService.buscarPorId(id);
            return ResponseEntity.ok(investimento);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestimentoAplicado> atualizarInvestimento(@PathVariable Long id, @RequestBody InvestimentoAplicado investimento) {
        try {
            InvestimentoAplicado atualizado = investimentoService.atualizarInvestimento(id, investimento);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInvestimento(@PathVariable Long id) {
        try {
            investimentoService.deletarInvestimento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}