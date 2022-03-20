package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "salario")
    private Float salario;

    @ManyToMany(mappedBy = "pessoas", fetch = FetchType.EAGER)
    private List<Emprestimo> emprestimos;


}
