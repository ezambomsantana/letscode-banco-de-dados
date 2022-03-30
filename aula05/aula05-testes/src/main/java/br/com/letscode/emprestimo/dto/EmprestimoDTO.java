package br.com.letscode.emprestimo.dto;

import br.com.letscode.emprestimo.model.Emprestimo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimoDTO {
    private Float valor;
    private String status;
    private Integer numParcelas;

    public static EmprestimoDTO convert(Emprestimo emprestimo) {
        EmprestimoDTO emp = new EmprestimoDTO();
        emp.setNumParcelas(emprestimo.getNumParcelas());
        emp.setStatus(emprestimo.getStatus());
        emp.setValor(emprestimo.getValor());
        return emp;
    }

}
