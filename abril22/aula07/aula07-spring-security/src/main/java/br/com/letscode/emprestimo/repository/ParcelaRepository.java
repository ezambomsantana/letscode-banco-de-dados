package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Integer> {
}
