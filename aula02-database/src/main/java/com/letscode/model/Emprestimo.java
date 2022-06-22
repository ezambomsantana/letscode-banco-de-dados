package com.letscode.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private Float valorTotal;

    @Column(name = "quantidade_parcela")
    private Integer quantidadeParcelas;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @OneToMany(mappedBy = "emprestimo")
    private List<Parcela> parcelas;

    @ManyToMany(mappedBy = "emprestimos")
    private List<Cliente> clientes;

}
