package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.PessoaBuscaDTO;
import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public Page<PessoaDTO> listPessoas(
            @RequestParam(name = "nome") List<String> nome,
            Pageable pageable) {
        return pessoaService.listAll(nome, pageable);
    }

    @GetMapping("/page")
    public Page<PessoaDTO> listPessoas(Pageable pageable) {
        return pessoaService.listAll(pageable);
    }

    @PostMapping("/")
    public PessoaDTO criarPessoa(@RequestBody PessoaDTO pessoa) {
        return pessoaService.criarPessoa(pessoa);
    }

    @DeleteMapping("/{cpf}")
    public void deletePessoa(@PathVariable String cpf) {
        pessoaService.deletePessoa(cpf);
    }




}
