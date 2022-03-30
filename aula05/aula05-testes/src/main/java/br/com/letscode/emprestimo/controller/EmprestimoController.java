package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.EmprestimoDTO;
import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.service.EmprestimoService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping
    public Iterable<EmprestimoDTO> findAllByWebQuerydsl(
            @QuerydslPredicate(root = Emprestimo.class) Predicate predicate) {
        return emprestimoService.findAll(predicate);
    }
}
