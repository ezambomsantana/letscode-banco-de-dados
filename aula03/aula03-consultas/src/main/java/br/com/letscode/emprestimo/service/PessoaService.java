package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
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

}
