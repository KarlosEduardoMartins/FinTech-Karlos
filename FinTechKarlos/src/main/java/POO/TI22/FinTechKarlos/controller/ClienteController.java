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
            Cliente novoCliente = clienteService.criarCliente(cliente);
            System.out.println("Cliente criado: " + novoCliente.getNome() + ", CPF: " + novoCliente.getCpf());
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
