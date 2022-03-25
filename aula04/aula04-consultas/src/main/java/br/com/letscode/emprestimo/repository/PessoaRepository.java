package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.model.PessoaSalario;
import br.com.letscode.emprestimo.projection.PessoaEmprestimoProjection;
import br.com.letscode.emprestimo.projection.PessoaParcelaProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor {

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






    //projection
    @Query(value = "select p.id as idPessoa, e.id as idEmprestimo, e.valor as valorEmprestimo " +
            "from pessoa p " +
            "join pessoa_emprestimo pe on p.id = pe.id_pessoa " +
            "join emprestimo e on pe.id_emprestimo = e.id", nativeQuery = true)
    List<PessoaEmprestimoProjection> listPessoaEmprestimo();

    //projection
    @Query(value = "select p.nome as nomePessoa, pa.valor as valorParcela " +
            "from pessoa p " +
            "join pessoa_emprestimo pe on p.id = pe.id_pessoa " +
            "join emprestimo e on pe.id_emprestimo = e.id " +
            "join parcela pa on e.id = pa.id_emprestimo", nativeQuery = true)
    List<PessoaParcelaProjection> listPessoaParcela();




    List<Pessoa> findByEnderecoOrderByNomeAsc(String nome);

    List<Pessoa> findByNome(String nome, Sort sort);

    List<Pessoa> findByNome(String nome, Pageable pageable);





}
