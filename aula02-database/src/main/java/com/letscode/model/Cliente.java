package com.letscode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "salario")
    private Float salario;

    @Column(name = "endereco")
    private String endereco;

    @ManyToMany
    @JoinTable(
            name = "cliente_emprestimo",
            joinColumns = {@JoinColumn(name = "id_cliente")},
            inverseJoinColumns = {@JoinColumn(name="id_emprestimo")}
    )
    @JsonIgnore
    private List<Emprestimo> emprestimos = new ArrayList<>();

}
