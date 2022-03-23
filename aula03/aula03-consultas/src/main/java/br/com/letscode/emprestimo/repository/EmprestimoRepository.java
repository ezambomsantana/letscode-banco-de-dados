package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    List<Emprestimo> findByValorGreaterThan(Float valor);
    List<Emprestimo> findByNumParcelasGreaterThan(Integer numParcelas);
    List<Emprestimo> findByNumParcelasLessThan(Integer numParcelas);

    List<Emprestimo> findByDataEmprestimoGreaterThan(LocalDateTime data);

    List<Emprestimo> findByDataEmprestimoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("SELECT SUM(e.valor) FROM emprestimo AS e")
    Integer sumValue();



}
