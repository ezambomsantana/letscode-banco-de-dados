package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.dto.PessoaSalario;
import br.com.letscode.emprestimo.repository.projection.PessoaEmprestimoProjection;
import br.com.letscode.emprestimo.repository.projection.PessoaParcelaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor<Pessoa>, QuerydslPredicateExecutor<Pessoa> {

    public String NOME = "nome";
    public String SALARIO = "salario";

    Optional<Pessoa> findByCpf(String cpf);

    Page<Pessoa> findByNome(String nome, Pageable pageable);

    List<Pessoa> findByNomeContaining(String nome);

    List<Pessoa> findByNomeStartsWith(String nome);

    List<Pessoa> findByNomeIgnoreCaseEndsWith(String nome);

    @Query("SELECT sum(salario) from pessoa")
    Double sumSalarios();
//jpql
    @Query("SELECT sum(salario), nome from pessoa where nome = :nome")
    Double sumSalarios(String nome);

    @Query("select avg(salario), endereco from pessoa group by endereco")
    List<Object[]> getMediaSalario();

    @Query("select new br.com.letscode.emprestimo.dto.PessoaSalario(avg(salario), endereco) from pessoa group by endereco")
    List<PessoaSalario> getMediaSalarioObject();



    // aula 04

    List<Pessoa> findByEnderecoOrderByNomeAsc(String endereco);


    List<Pessoa> findByEndereco(String endereco, Sort sort);


    List<Pessoa> findByEndereco(String endereco, Pageable pageable);



    @Query(
           value = "select p.nome as nomePessoa, pa.valor as valorParcela " +
                   "from pessoa p " +
                   "join pessoa_emprestimo pe on p.id = pe.id_pessoa " +
                   "join emprestimo e on e.id = pe.id_emprestimo " +
                   "join parcela pa on e.id = pa.id_emprestimo " +
                   "where nome = :nome", nativeQuery = true
    )
    List<PessoaParcelaProjection> listPessoaValorParcela(String nome);

    @Query(
            value = "select p.nome as nomePessoa, p.salario as salarioPessoa, " +
                    "e.valor as valorEmprestimo, e.num_parcelas as numParcelas, e.status as status " +
                    "from pessoa p " +
                    "join pessoa_emprestimo pe on p.id = pe.id_pessoa " +
                    "join emprestimo e on e.id = pe.id_emprestimo " , nativeQuery = true
    )
    List<PessoaEmprestimoProjection> listPessoaEmprestimo();





}
