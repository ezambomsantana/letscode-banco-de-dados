package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.model.PessoaSalario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    List<Pessoa> findByCpf(String cpf);

    List<Pessoa> findByNome(String nome);

    List<Pessoa> findByNomeContaining(String nome);

    List<Pessoa> findByNomeStartsWith(String nome);

    List<Pessoa> findByNomeIgnoreCaseEndsWith(String nome);

    @Query("SELECT sum(salario) from pessoa")
    Double sumSalarios();

    @Query("SELECT sum(salario), nome from pessoa where nome = :nome")
    Double sumSalarios(String nome);

    @Query("select avg(salario), endereco from pessoa group by endereco")
    List<Object[]> getMediaSalario();

    @Query("select new br.com.letscode.emprestimo.model.PessoaSalario(avg(salario), endereco) from pessoa group by endereco")
    List<PessoaSalario> getMediaSalarioObject();


}
