package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "pessoa_emprestimo")
public class PessoaEmprestimo {

    @EmbeddedId
    private PessoaEmprestimoKey pessoaEmprestimoKey;

    @ManyToOne
    @MapsId("idEmprestimo")
    @JoinColumn(name = "id_emprestimo")
    private Emprestimo emprestimo;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "assinatura")
    private String assinatura;

}
