package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Parcela;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.model.PessoaSalario;
import br.com.letscode.emprestimo.repository.EmprestimoRepository;
import br.com.letscode.emprestimo.repository.ParcelaRepository;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final PessoaRepository pessoaRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ParcelaRepository parcelaRepository;

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

}
