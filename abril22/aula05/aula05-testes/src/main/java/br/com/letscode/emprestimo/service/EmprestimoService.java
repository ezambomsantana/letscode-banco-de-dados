package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.EmprestimoDTO;
import br.com.letscode.emprestimo.model.*;
import br.com.letscode.emprestimo.model.QEmprestimo;
import br.com.letscode.emprestimo.model.QParcela;
import br.com.letscode.emprestimo.repository.EmprestimoRepository;
import br.com.letscode.emprestimo.repository.ParcelaRepository;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final PessoaRepository pessoaRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ParcelaRepository parcelaRepository;


    @Autowired
    private EntityManager entityManager;


    public void test() {

        QEmprestimo customer = QEmprestimo.emprestimo;
        JPAQuery<?> query = new JPAQuery<Void>(entityManager);
        Emprestimo emprestimo = query.select(customer)
                .from(customer)
                .where(customer.status.eq("ABERTO"))
                .fetchFirst();

        System.out.println(emprestimo.getId());


        List<Emprestimo> emprestimos = query.select(customer)
                .from(customer)
                .where(customer.status.eq("ABERTO"))
                .fetch();

        emprestimos.forEach(x -> System.out.println(x.getId()));

        QParcela parcela = QParcela.parcela;
        List<Emprestimo> emprestimosJoin = query.select(customer)
                .from(customer)
                .join(customer.parcelas, parcela).on(parcela.id.eq(customer.id))
                .where(customer.status.eq("ABERTO"))
                .orderBy(customer.status.asc())
                .fetch();

        emprestimos.forEach(x -> System.out.println(x.getId()));


        QEmprestimo emp = QEmprestimo.emprestimo;

        Predicate predicate = emp.status.eq("ABERTO");

        System.out.println("--------");
        Iterable<Emprestimo> emps = emprestimoRepository.findAll(predicate);
        emps.forEach(x -> System.out.println(x.getId() + " " + x.getValor()));

        QEmprestimo emp2 = QEmprestimo.emprestimo;
        Predicate predicate1 = emp.status.eq("ABERTO")
                .and(emp.numParcelas.gt(10));
        System.out.println("--------");
         emps = emprestimoRepository.findAll(predicate);
        emps.forEach(x -> System.out.println(x.getId() + " " + x.getValor()));


    }

    public void criarEmprestimo() {

        Pessoa p = Pessoa.builder()
                .nome("Felipe Santana")
                .cpf("1234")
                .endereco("Rua abc")
                .salario(1000F)
                .build();

        p = pessoaRepository.save(p);

        Emprestimo emprestimo = Emprestimo.builder()
                .numParcelas(5)
                .status("ABERTO")
                .dataEmprestimo(LocalDateTime.now())
                .valor(10000F)
                .pessoas(new ArrayList<>())
                .build();

        emprestimo.getPessoas().add(p);

        emprestimo = emprestimoRepository.save(emprestimo);

        Parcela parcela = new Parcela();
        parcela.setEmprestimo(emprestimo);
        parcela.setNum(1);
        parcela.setValor(10000F);
        parcela.setStatus("ABERTO");

        parcelaRepository.save(parcela);

    }

    public void deleteParcela() {
        Parcela parcela = parcelaRepository.getById(8);
        parcelaRepository.delete(parcela);
    }

    public void buscaPessoa() {
        Pessoa pessoa = pessoaRepository.findById(10).orElseThrow(() -> new RuntimeException("Pessoa nao existe"));
        System.out.println(pessoa.getId() + " " + pessoa.getNome());

        List<Pessoa> pessoas = pessoaRepository.findAll();
        pessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome()));

        System.out.println("-------");

        //pessoas = pessoaRepository.findByCpf("1234");
       // pessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome()));


       // pessoas = pessoaRepository.findByNome("Eduardo");
       // pessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome()));

        System.out.println("------");

        pessoas = pessoaRepository.findByNomeIgnoreCaseEndsWith("Ana");
        pessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome()));

        System.out.println("----");
        Double soma = pessoaRepository.sumSalarios();
        System.out.println(soma);


        System.out.println("----");
        soma = pessoaRepository.sumSalarios("Eduardo");
        System.out.println(soma);


    }

    public void buscaEmprestimo() {

       // List<Emprestimo> emprestimos = emprestimoRepository.findByValorGreaterThan(15000F);
      //  emprestimos.forEach(e -> System.out.println(e.getId()));


       // List<Emprestimo> emprestimos = emprestimoRepository.findByNumParcelasLessThanEqual(5);
       // emprestimos.forEach(e -> System.out.println(e.getId()));


        LocalDateTime data = LocalDateTime.of(2022, 03, 23, 0, 0, 1);

        //List<Emprestimo> emprestimos = emprestimoRepository.findByDataEmprestimoGreaterThan(data);
        //emprestimos.forEach(e -> System.out.println(e.getId()));

        List<Emprestimo> emprestimos = emprestimoRepository.findByDataEmprestimoBetween(data, LocalDateTime.now());
        emprestimos.forEach(e -> System.out.println(e.getId()));


    }


    public void groupBy() {
        List<Object[]> medias = pessoaRepository.getMediaSalario();
        for (Object[] media : medias) {
            System.out.println(media[0]);
            System.out.println(media[1]);
        }

        System.out.println("------");
        List<PessoaSalario> pessoaSalarios = pessoaRepository.getMediaSalarioObject();
        pessoaSalarios.forEach(p -> System.out.println(p.getMedia() + " " + p.getEndereco()));

    }

    public Iterable<EmprestimoDTO> findAll(Predicate predicate) {
        Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll(predicate);
        return StreamSupport.stream(emprestimos.spliterator(), false)
                .map(EmprestimoDTO::convert).collect(Collectors.toList());

    }
}
