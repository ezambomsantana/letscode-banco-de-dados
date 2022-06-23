package com.letscode.cliente.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Document("clientes")
public class Cliente {
    @Id
    private String id;
    private String nome;
    private String cpf;
    private Float salario;
    private List<String> endereco;
    private List<Telefone> telefones;
}
