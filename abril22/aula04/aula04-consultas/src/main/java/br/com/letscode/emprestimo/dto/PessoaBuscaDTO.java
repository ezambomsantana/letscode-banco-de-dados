package br.com.letscode.emprestimo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PessoaBuscaDTO {
    private String nome;
    private String endereco;
    private String cpf;
    private Double salario;
}
