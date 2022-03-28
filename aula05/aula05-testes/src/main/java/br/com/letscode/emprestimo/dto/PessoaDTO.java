package br.com.letscode.emprestimo.dto;

import br.com.letscode.emprestimo.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nome;
    private Float salario;
    private String endereco;
    private String cpf;

    public static PessoaDTO convert(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(pessoa.getNome());
        dto.setSalario(pessoa.getSalario());
        dto.setCpf(pessoa.getCpf());
        dto.setEndereco(pessoa.getEndereco());
        return dto;
    }

}
