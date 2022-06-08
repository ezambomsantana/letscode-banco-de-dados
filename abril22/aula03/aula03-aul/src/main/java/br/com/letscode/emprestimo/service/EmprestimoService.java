package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Parcela;
import br.com.letscode.emprestimo.model.Pessoa;
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

        pessoas = pessoaRepository.findByCpf("1234");
        pessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome()));


        pessoas = pessoaRepository.findByNome("Eduardo");
        pessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome()));




    }


}
