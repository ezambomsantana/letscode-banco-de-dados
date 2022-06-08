package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.PessoaEmprestimo;
import br.com.letscode.emprestimo.model.PessoaEmprestimoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaEmprestimoRepository extends JpaRepository<PessoaEmprestimo, PessoaEmprestimoKey> {
}
