package com.letscode.cliente.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document("emprestimos")
public class Emprestimo {
    @Id
    private String id;
    private Float valorTotal;
    private Integer quantidadeParcelas;
    private LocalDate dataEmprestimo;
    private String idCliente;

    //@Transient
    private List<Parcela> parcelas;
}
