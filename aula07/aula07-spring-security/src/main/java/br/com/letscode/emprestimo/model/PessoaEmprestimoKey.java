package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class PessoaEmprestimoKey implements Serializable {

    @Column(name = "id_emprestimo")
    private Integer idEmprestimo;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

}
