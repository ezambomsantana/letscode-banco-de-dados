package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.EmprestimoDTO;
import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.service.EmprestimoService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<EmprestimoDTO> listEmprestimos(
            @QuerydslPredicate(root = Emprestimo.class) Predicate predicate,
            Pageable pageable) {
        return emprestimoService.listEmprestimos(predicate, pageable);
    }


    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public List<EmprestimoDTO> listEmprestimosJoin() {
        return emprestimoService.listEmprestimosJoin();
    }

    @PostMapping
    public EmprestimoDTO criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        return emprestimoService.criarEmprestimo(emprestimoDTO);
    }


}
