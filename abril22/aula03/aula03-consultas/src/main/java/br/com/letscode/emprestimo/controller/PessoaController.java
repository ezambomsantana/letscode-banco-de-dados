package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/")
    public List<PessoaDTO> listPessoas() {
        return pessoaService.listAll();
    }

}
