package com.letscode.cliente.controller;

import com.letscode.cliente.model.Cliente;
import com.letscode.cliente.model.Emprestimo;
import com.letscode.cliente.model.Parcela;
import com.letscode.cliente.repository.ClienteRepository;
import com.letscode.cliente.repository.EmprestimoRepository;
import com.letscode.cliente.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
public class EmprestimoController {

    private final ClienteRepository clienteRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ParcelaRepository parcelaRepository;

    @GetMapping
    public Page<Emprestimo> listEmprestimo(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAll(pageable);
      //  for (Emprestimo emprestimo : emprestimos) {
      //      List<Parcela> parcelas = parcelaRepository.findByIdEmprestimo(emprestimo.getId());
     //       emprestimo.setParcelas(parcelas);
     //   }
        return emprestimos;
    }

    @GetMapping("/{id}")
    public Emprestimo getEmprestimo(@PathVariable String id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo nao encontrado."));
      //  List<Parcela> parcelas = parcelaRepository.findByIdEmprestimo(emprestimo.getId());
      //  emprestimo.setParcelas(parcelas);
        return emprestimo;
    }

    @PostMapping
    public Emprestimo saveEmprestimo(@RequestBody Emprestimo emprestimo) {
        Cliente cliente = clienteRepository.findById(emprestimo.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        emprestimo.setId(UUID.randomUUID().toString());
        Emprestimo emprestimoDB = emprestimoRepository.save(emprestimo);

//        for (Parcela parcela : emprestimo.getParcelas()) {
 //           parcela.setId(UUID.randomUUID().toString());
  //          parcela.setIdEmprestimo(emprestimoDB.getId());
  //          parcelaRepository.save(parcela);
  //      }
        return emprestimo;
    }

    @DeleteMapping("/{id}")
    public void deleteEmprestimo(@PathVariable String id) {
        parcelaRepository.deleteByIdEmprestimo(id);
        emprestimoRepository.deleteById(id);
    }


}
