package POO.TI22.FinTechKarlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import POO.TI22.FinTechKarlos.model.Cliente;
import POO.TI22.FinTechKarlos.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.criarOuObterCliente(cliente);
            System.out.println("\nCliente criado: " + novoCliente.getNome() + ", CPF: " + novoCliente.getCpf() + ".");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Cliente criado com sucesso!\n" + "\nNome: " + novoCliente.getNome() + "\nCPF: " + novoCliente.getCpf());
        } catch (Exception e) {
            System.err.println("Erro ao tentar criar cliente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar cliente: " + e.getMessage());
        }
    }
}
