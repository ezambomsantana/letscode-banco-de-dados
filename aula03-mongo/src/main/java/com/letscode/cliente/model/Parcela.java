package com.letscode.cliente.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
//@Document("parcelas")
public class Parcela {
    @Id
    private String id;
    private Float valor;
    private LocalDate dataPagamento;
    private Integer numeroSequencial;
    private String idEmprestimo;
}
