package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name="salario")
    private Float salario;
    @Column(name="cpf")
    private String cpf;
    @Column(name="endereco")
    private String endereco;

    private List<Emprestimo> emprestimos;
}
