package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping(path = "/page")
    Page<PessoaDTO> listPessoasPage(@PageableDefault(value = 10, page = 0) Pageable pageable) {
        return pessoaService.listAll(pageable);
    }


}
