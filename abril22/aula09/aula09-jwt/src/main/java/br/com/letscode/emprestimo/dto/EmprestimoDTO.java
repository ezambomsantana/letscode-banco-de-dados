package br.com.letscode.emprestimo.dto;

import br.com.letscode.emprestimo.model.Emprestimo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class EmprestimoDTO {
    private Float valor;
    private String status;
    private Integer numParcelas;

    private List<PessoaDTO> pessoas = new ArrayList<>();
    private List<ParcelaDTO> parcelas = new ArrayList<>();

    public static EmprestimoDTO convert(Emprestimo emprestimo) {
        EmprestimoDTO emp = new EmprestimoDTO();
        emp.setNumParcelas(emprestimo.getNumParcelas());
        emp.setStatus(emprestimo.getStatus());
        emp.setValor(emprestimo.getValor());

        emp.getPessoas().addAll(emprestimo.getPessoas().stream().map(PessoaDTO::convert).collect(Collectors.toList()));
        emp.getParcelas().addAll(emprestimo.getParcelas().stream().map(ParcelaDTO::convert).collect(Collectors.toList()));

        return emp;
    }

}
