package com.letscode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "parcela")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "num_sequencial")
    private Integer numeroSequencial;

    @ManyToOne
    @JoinColumn(name = "id_emprestimo")
    @JsonIgnore
    private Emprestimo emprestimo;

}
