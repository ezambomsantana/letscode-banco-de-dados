package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.PessoaBuscaDTO;
import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Parcela;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.model.QPessoa;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import br.com.letscode.emprestimo.repository.projection.PessoaEmprestimoProjection;
import br.com.letscode.emprestimo.repository.projection.PessoaParcelaProjection;
import br.com.letscode.emprestimo.repository.specification.PessoaSpecification;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Page<PessoaDTO> listAll(List<String> nome, Pageable pageable) {
        return pessoaRepository
                .findByNome(nome.get(0), pageable)
                .map(PessoaDTO::convert);
    }


    public Page<PessoaDTO> listAll(Pageable pageable) {
        return pessoaRepository
                .findAll(pageable)
                .map(PessoaDTO::convert);
    }


    public PessoaDTO criarPessoa(PessoaDTO pessoa) {
        Pessoa pessoaBD = pessoaRepository.save(Pessoa.convert(pessoa));
        return PessoaDTO.convert(pessoaBD);
    }

    public void deletePessoa(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException());
        pessoaRepository.delete(pessoa);
    }




    public void ordenacao() {
        List<Pessoa> pessoas = pessoaRepository.findByEnderecoOrderByNomeAsc("sp");
        pessoas.forEach(p -> System.out.println(p.getNome() + " " + p.getEndereco()));

        System.out.println("----");

        pessoas = pessoaRepository.findByEndereco(
                "sp",
                Sort.by(Sort.Direction.ASC, "endereco", "nome"));
        pessoas.forEach(p -> System.out.println(p.getNome() + " " + p.getEndereco()));
    }

    public void paginacao() {
        List<Pessoa> pessoas = pessoaRepository.findByEndereco(
                "sp",
                PageRequest.of(2,3, Sort.by(Sort.Direction.ASC, "endereco", "nome"))
        );
        pessoas.forEach(p -> System.out.println(p.getNome() + " " + p.getEndereco()));

        Page<Pessoa> pessoasPage = pessoaRepository.findAll(
                PageRequest.of(2,3, Sort.by(Sort.Direction.ASC, "endereco", "nome"))
        );
        pessoasPage.forEach(p -> System.out.println(p.getNome() + " " + p.getEndereco()));
        System.out.println(pessoasPage.getTotalElements()
                + " " + pessoasPage.getTotalPages()
                + " " + pessoasPage.getSize());
    }

    public void projection() {


        List<PessoaParcelaProjection> pessoas = pessoaRepository.listPessoaValorParcela("Luiz");
        pessoas.forEach(x -> System.out.println(x.getNomePessoa() + " " + x.getValorParcela()));

        System.out.println("----");

        List<PessoaEmprestimoProjection> pessoasEmprestimo = pessoaRepository.listPessoaEmprestimo();
        pessoasEmprestimo.forEach(x ->
                System.out.println(x.getNomePessoa() + " " + x.getSalarioPessoa()
                + " " + x.getValorEmprestimo() + " " + x.getNumParcelas() + " "
                + x.getStatus()));
    }


    public List<PessoaDTO> queryPessoaSpecification(PessoaBuscaDTO pessoaBuscaDTO) {

        Specification<Pessoa> specification = Specification.where(null);

        if (pessoaBuscaDTO.getNome() != null) {
            specification = specification.and(PessoaSpecification.filterByName(pessoaBuscaDTO.getNome()));
        }

        if (pessoaBuscaDTO.getEndereco() != null) {
            specification = specification.and(PessoaSpecification.filterByEndereco(pessoaBuscaDTO.getEndereco()));
        }

        if (pessoaBuscaDTO.getSalario() != null) {
            specification = specification.and(PessoaSpecification.filterBySalarioGreaterThan(pessoaBuscaDTO.getSalario()));
        }

        return pessoaRepository
                .findAll(specification)
                .stream()
                .map(PessoaDTO::convert)
                .collect(Collectors.toList());

    }


    private final EntityManager entityManager;

    public void testPessoa() {

        JPAQuery<?> query = new JPAQuery<Void>(entityManager);
        QPessoa pessoa = QPessoa.pessoa;
        Pessoa pessoaBD = query.select(pessoa)
                .from(pessoa)
                .where(pessoa.nome.eq("Eduardo"))
                .fetchFirst();

        System.out.println(pessoaBD.getId() + " " + pessoaBD.getNome() + " " + pessoaBD.getEndereco());


        // ?nome=Eduardo&salario=100&endereco=sp
        Predicate predicate = pessoa.nome.eq("Eduardo")
                .and(pessoa.salario.gt(1000))
                .and(pessoa.endereco.eq("sp"));

        query = new JPAQuery<Void>(entityManager);
        List<Pessoa> listPessoas = query.select(pessoa)
                .from(pessoa)
                .where(predicate)
                .fetch();

        listPessoas.forEach(p -> System.out.println(p.getId() + " " + p.getNome() + " " + p.getSalario() + " " + p.getEndereco() ));

        System.out.println("-------");
        Iterable<Pessoa> pessoasList = pessoaRepository.findAll(predicate);
        pessoasList.forEach(p -> System.out.println(p.getId() + " " + p.getNome() + " " + p.getSalario() + " " + p.getEndereco() ));

    }

    public Page<PessoaDTO> queryPessoaPredicate(Predicate predicate, Pageable pageable) {
        return pessoaRepository.findAll(predicate, pageable).map(PessoaDTO::convert);
    }
}
