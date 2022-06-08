package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.ParcelaDTO;
import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Parcela;
import br.com.letscode.emprestimo.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;

    public Parcela criarParcela(ParcelaDTO parcelaDTO, Emprestimo emprestimo) {
        Parcela parcela = new Parcela();
        parcela.setNum(parcelaDTO.getNum());
        parcela.setValor(parcelaDTO.getValor());
        parcela.setStatus(parcelaDTO.getStatus());
        parcela.setEmprestimo(emprestimo);
        return parcelaRepository.save(parcela);
    }

}
