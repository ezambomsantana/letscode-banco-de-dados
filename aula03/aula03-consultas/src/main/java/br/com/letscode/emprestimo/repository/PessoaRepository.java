package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.model.PessoaSalario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    //Pessoa findByCpf(String cpf);

    Optional<Pessoa> findByCpf(String cpf);

    List<Pessoa> findByNome(String nome);



    @Query("SELECT AVG(p.salario), endereco FROM pessoa AS p GROUP BY endereco")
    List<Object[]> getMediaSalario();


    @Query("SELECT new br.com.letscode.emprestimo.model.PessoaSalario(AVG(p.salario), endereco) FROM pessoa AS p GROUP BY endereco")
    List<PessoaSalario> getMediaSalarioObjeto();


    @Query(value="select * from pessoa p where p.nome = :nome", nativeQuery=true)
    List<Pessoa> getPessoasNome(String nome);

    List<Pessoa> findByNomeContaining(String title);
    List<Pessoa> findByNomeStartsWith(String title);



}
