package com.letscode.cliente.controller;

import com.letscode.cliente.model.Cliente;
import com.letscode.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        Cliente clienteDB = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteDB == null) {
            cliente.setId(UUID.randomUUID().toString());
            return clienteRepository.save(cliente);
        }
        throw new RuntimeException("Cliente já cadastrado");
    }


}
