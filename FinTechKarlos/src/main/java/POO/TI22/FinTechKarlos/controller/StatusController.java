package POO.TI22.FinTechKarlos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);
    private final List<String> statusList = new ArrayList<>();

    public StatusController() {
        statusList.add("API FinTech Karlos está online!");
    }

    @PostMapping
    public ResponseEntity<String> adicionarStatus(@RequestBody String novoStatus) {
        logger.debug("Requisição POST para adicionar status: {}", novoStatus);
        statusList.add(novoStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body("Status adicionado com sucesso: " + novoStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> buscarStatusPorId(@PathVariable int id) {
        logger.debug("Requisição GET para buscar status com ID: {}", id);
        if (id >= 0 && id < statusList.size()) {
            return ResponseEntity.ok(statusList.get(id));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status com ID " + id + " não encontrado.");
    }

    @GetMapping
    public ResponseEntity<List<String>> verificarStatus() {
        logger.debug("Requisição GET para listar todos os status.");
        return ResponseEntity.ok(statusList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarStatus(@PathVariable int id, @RequestBody String statusAtualizado) {
        logger.debug("Requisição PUT para atualizar status com ID {}: {}", id, statusAtualizado);
        if (id >= 0 && id < statusList.size()) {
            statusList.set(id, statusAtualizado);
            return ResponseEntity.ok("Status com ID " + id + " atualizado para: " + statusAtualizado);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status com ID " + id + " não encontrado para atualização.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStatus(@PathVariable int id) {
        logger.debug("Requisição DELETE para o status com ID: {}", id);
        if (id >= 0 && id < statusList.size()) {
            statusList.remove(id);
            return ResponseEntity.noContent().build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status com ID " + id + " não encontrado para exclusão.");
    }
}