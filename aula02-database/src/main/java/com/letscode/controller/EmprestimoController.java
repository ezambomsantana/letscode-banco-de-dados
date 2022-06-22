package com.letscode.controller;

import com.letscode.model.Cliente;
import com.letscode.model.Emprestimo;
import com.letscode.model.Parcela;
import com.letscode.respository.ClienteRepository;
import com.letscode.respository.EmprestimoRepository;
import com.letscode.respository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final ParcelaRepository parcelaRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Emprestimo> listEmprestimos(
            @RequestParam(name = "valor", required = false) Float valor) {
        if (valor != null) {
            return emprestimoRepository.findByValorTotalGreaterThan(valor);
        }
        return emprestimoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Emprestimo getEmprestimo(@PathVariable Integer id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo nao encontrado."));
    }

    @PostMapping
    public Emprestimo saveEmprestimo(@RequestBody Emprestimo emprestimo) {
        emprestimo = emprestimoRepository.save(emprestimo);
        for (Parcela parcela : emprestimo.getParcelas()) {
            parcela.setEmprestimo(emprestimo);
            parcelaRepository.save(parcela);
        }
        for (Cliente cliente : emprestimo.getClientes()) {
            Cliente clienteDb = clienteRepository.findByCpf(cliente.getCpf());
            if (clienteDb != null) {
                clienteDb.getEmprestimos().add(emprestimo);
                cliente.setId(clienteDb.getId());
                clienteRepository.save(clienteDb);
            } else {
                cliente.getEmprestimos().add(emprestimo);
                clienteRepository.save(cliente);
            }
        }
        return emprestimo;
    }

    @DeleteMapping("/{id}")
    public void deleteEmprestimo(@PathVariable Integer id) {
        emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo nao encontrado."));
        emprestimoRepository.deleteById(id);
    }

}
