package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.EmprestimoDTO;
import br.com.letscode.emprestimo.dto.ParcelaDTO;
import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.*;
import br.com.letscode.emprestimo.model.QEmprestimo;
import br.com.letscode.emprestimo.model.QParcela;
import br.com.letscode.emprestimo.repository.EmprestimoRepository;
import br.com.letscode.emprestimo.repository.ParcelaRepository;
import br.com.letscode.emprestimo.repository.PessoaEmprestimoRepository;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final ParcelaService parcelaService;
    private final EmprestimoRepository emprestimoRepository;
    private final PessoaRepository pessoaRepository;
    private final PessoaEmprestimoRepository pessoaEmprestimoRepository;


    public Page<EmprestimoDTO> listEmprestimos(Predicate predicate, Pageable pageable) {
        return emprestimoRepository.findAll(predicate, pageable).map(EmprestimoDTO::convert);
    }

    public List<EmprestimoDTO> listEmprestimosJoin() {

        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos
                .stream()
                .map(EmprestimoDTO::convert)
                .collect(Collectors.toList());

    }

    public EmprestimoDTO criarEmprestimo(EmprestimoDTO emprestimoDTO) {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setValor(emprestimoDTO.getValor());
        emprestimo.setDataEmprestimo(LocalDateTime.now());
        emprestimo.setStatus(emprestimoDTO.getStatus());
        emprestimo.setNumParcelas(emprestimoDTO.getNumParcelas());

        emprestimo = emprestimoRepository.save(emprestimo);

        for (ParcelaDTO parcelaDTO : emprestimoDTO.getParcelas()) {
            Parcela parcela = parcelaService.criarParcela(parcelaDTO, emprestimo);
            emprestimo.getParcelas().add(parcela);
        }

        for (PessoaDTO pessoa : emprestimoDTO.getPessoas()) {
            Pessoa pessoaBD = pessoaRepository.findByCpf(pessoa.getCpf()).get();

            PessoaEmprestimoKey key = new PessoaEmprestimoKey();
            key.setIdPessoa(pessoaBD.getId());
            key.setIdEmprestimo(emprestimo.getId());

            PessoaEmprestimo pessoaEmprestimo = new PessoaEmprestimo();
            pessoaEmprestimo.setEmprestimo(emprestimo);
            pessoaEmprestimo.setPessoa(pessoaBD);
            pessoaEmprestimo.setAssinatura(UUID.randomUUID().toString());
            pessoaEmprestimo.setPessoaEmprestimoKey(key);

            emprestimo.getPessoas().add(pessoaEmprestimoRepository.save(pessoaEmprestimo));

        }
        return EmprestimoDTO.convert(emprestimo);
    }
}
