package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.projection.PessoaEmprestimoProjection;
import br.com.letscode.emprestimo.projection.PessoaParcelaProjection;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import br.com.letscode.emprestimo.specification.PessoaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<PessoaDTO> listAll() {
        return pessoaRepository
                .findAll()
                .stream()
                .map(PessoaDTO::convert)
                .collect(Collectors.toList());
    }

    public Page<PessoaDTO> listAll(Pageable pageable) {
        return pessoaRepository
                .findAll(pageable)
                .map(PessoaDTO::convert);
    }


    public void listPessoasOrders() {
        List<Pessoa> pessoas = pessoaRepository.findByEnderecoOrderByNomeAsc("sp");
        pessoas.forEach(x -> System.out.println(x.getNome()));


        List<Pessoa> pessoas1 = pessoaRepository.findByNome("Eduardo", Sort.by(Sort.Direction.ASC, "salario"));
        pessoas1.forEach(x -> System.out.println(x.getNome() + x.getSalario()));


        List<Pessoa> pessoas2 = pessoaRepository.findAll(Sort.by(Sort.Direction.DESC, "salario"));
        pessoas2.forEach(x -> System.out.println(x.getNome()));

    }

    public void listPessoasPage() {
        List<Pessoa> pessoas1 = pessoaRepository.findByNome("Eduardo", PageRequest.of(2, 4));
        pessoas1.forEach(x -> System.out.println(x.getNome() + x.getSalario()));

        Page<Pessoa> pessoas2 = pessoaRepository.findAll(PageRequest.of(2, 4));
        pessoas2.forEach(x -> System.out.println(x.getNome() + x.getSalario()));
        System.out.println(pessoas2.getTotalElements() + " " + pessoas2.getTotalPages());


        Page<Pessoa> pessoas3 = pessoaRepository.findAll(PageRequest.of(2, 4, Sort.by(Sort.Direction.ASC, "nome")));
        pessoas3.forEach(x -> System.out.println(x.getNome() + x.getSalario()));

    }



    public void listAllPr() {
        List<PessoaEmprestimoProjection> pr = pessoaRepository.listPessoaEmprestimo();
        pr.forEach(x -> System.out.println(x.getIdEmprestimo() + "  " + x.getIdPessoa() + " " + x.getValorEmprestimo()));

        System.out.println("----");

        List<PessoaParcelaProjection> pr2 = pessoaRepository.listPessoaParcela();
        pr2.forEach(x -> System.out.println(x.getNomePessoa() + "  " + x.getValorParcela()));
    }


    public void usePredicate() {

        List<Pessoa> p = pessoaRepository.findAll(PessoaSpecification.filterByName("Eduardo"));
        p.forEach(x -> System.out.println(x.getNome() + "  " + x.getEndereco()));


        System.out.println("----");
        p = pessoaRepository.findAll(PessoaSpecification.filterByName("Eduardo").and(PessoaSpecification.filterByEnredereco("sp")));
        p.forEach(x -> System.out.println(x.getNome() + "  " + x.getEndereco()));



    }
}
