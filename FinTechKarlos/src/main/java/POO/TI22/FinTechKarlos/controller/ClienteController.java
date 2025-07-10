package POO.TI22.FinTechKarlos.controller;

import POO.TI22.FinTechKarlos.config.ApiConfigManager;
import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) {
        logger.debug("Recebida requisição para criar cliente: {}", cliente.getNome());
        try {
            Cliente novoCliente = clienteService.criarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar cliente.", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        logger.debug("Recebida requisição para listar todos os clientes.");
        List<Cliente> clientes = clienteService.listarTodos();
        ApiConfigManager config1 = ApiConfigManager.getInstance();
        ApiConfigManager config2 = ApiConfigManager.getInstance();

        System.out.println("API Key de config1: " + config1.getApiKey());
        System.out.println("API Key de config2: " + config2.getApiKey());
        System.out.println("As instâncias são iguais? " + (config1 == config2));
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        logger.debug("Recebida requisição para buscar cliente com CPF: {}", cpf);
        try {
            Cliente cliente = clienteService.buscarPorCpf(cpf);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String cpf, @Valid @RequestBody Cliente cliente) {
        logger.debug("Recebida requisição para atualizar cliente com CPF: {}", cpf);
        try {
            Cliente atualizado = clienteService.atualizarCliente(cpf, cliente);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        logger.debug("Recebida requisição para deletar cliente com CPF: {}", cpf);
        try {
            clienteService.deletarCliente(cpf);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}