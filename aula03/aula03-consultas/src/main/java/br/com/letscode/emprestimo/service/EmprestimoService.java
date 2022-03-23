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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final PessoaRepository pessoaRepository;
    private final ParcelaRepository parcelaRepository;

    public void buscaPessoa() {


        LocalDateTime date = LocalDateTime.of(2020, 10, 7, 2,0);
        System.out.println("data entre");
        List<Emprestimo> emprestimos2 = emprestimoRepository.findByDataEmprestimoBetween(date, LocalDateTime.now());
        emprestimos2.forEach(emprestimo -> System.out.println(emprestimo.getId()));

        System.out.println("-----");

        List<Pessoa> pGETALL = pessoaRepository.findAll();
        for (Pessoa p : pGETALL) {
            System.out.println(p.getEmprestimos());
        }

        System.out.println("-----");



        List<Pessoa> pessoasNomeCon = pessoaRepository.findByNomeContaining("Edu");
        pessoasNomeCon.stream().forEach(x -> System.out.println(x.getNome()));

        System.out.println("-----");

        List<Pessoa> pessoasNome = pessoaRepository.getPessoasNome("Eduardo");
        pessoasNome.stream().forEach(x -> System.out.println(x.getNome()));

        List<Object[]> salarios = pessoaRepository.getMediaSalario();
        for (Object[] sal : salarios) {
            System.out.println(sal[0]);
            System.out.println(sal[1]);
        }

        List<PessoaSalario> pSal = pessoaRepository.getMediaSalarioObjeto();
        pSal.stream().forEach(x -> System.out.println(x.getSalario()));



        Optional<Pessoa> p = pessoaRepository.findByCpf("321");
        p.ifPresent(pes -> System.out.println(pes.getNome()));


        List<Pessoa> pessoas = pessoaRepository.findByNome("Eduardo");
        pessoas.forEach(pes -> System.out.println(pes.getNome() + " " + pes.getCpf()));


        List<Emprestimo> emprestimos = emprestimoRepository.findByValorGreaterThan(1000F);
        emprestimos.forEach(emprestimo -> System.out.println(emprestimo.getId()));


        emprestimos = emprestimoRepository.findByNumParcelasGreaterThan(3);
        emprestimos.forEach(emprestimo -> System.out.println(emprestimo.getId()));

        System.out.println("menor que");
        emprestimos = emprestimoRepository.findByNumParcelasLessThan(10);
        emprestimos.forEach(emprestimo -> System.out.println(emprestimo.getId()));


        System.out.println("maior que data");
        emprestimos = emprestimoRepository.findByDataEmprestimoGreaterThan(date);
        emprestimos.forEach(emprestimo -> System.out.println(emprestimo.getId()));

        Optional<Float> value = emprestimos.stream()
                .map(x -> x.getValor())
                        .reduce((x, y) -> x + y);
        System.out.println(value.get());

        System.out.println(emprestimoRepository.sumValue());






    }


    public void salvarEmprestimo() {

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("4das");
        pessoa.setNome("Eduardo");
        pessoa.setSalario(10000F);
        pessoa.setEndereco("Rua ABC");

        Pessoa p = pessoaRepository.save(pessoa);

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setDataEmprestimo(LocalDateTime.now());
        emprestimo.setValor(1000F);
        emprestimo.setStatus("ABERTO");
        emprestimo.setPessoas(new ArrayList<>());
        emprestimo.setNumParcelas(5);


        emprestimo.getPessoas().add(p);
        Emprestimo emp = emprestimoRepository.save(emprestimo);


        Parcela parcela = new Parcela();
        parcela.setEmprestimo(emp);
        parcela.setValor(2000F);
        parcela.setStatus("ABERTO");
        parcela.setNum(1);

        parcelaRepository.save(parcela);


    }

}
