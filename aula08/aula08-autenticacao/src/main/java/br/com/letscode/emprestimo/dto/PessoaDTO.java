package br.com.letscode.emprestimo.dto;

import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.model.PessoaEmprestimo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nome;
    private Float salario;
    private String endereco;
    private String cpf;
    private String assinatura;

    public static PessoaDTO convert(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(pessoa.getNome());
        dto.setSalario(pessoa.getSalario());
        dto.setCpf(pessoa.getCpf());
        dto.setEndereco(pessoa.getEndereco());
        return dto;
    }

    public static PessoaDTO convert(PessoaEmprestimo pessoaEmprestimo) {
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(pessoaEmprestimo.getPessoa().getNome());
        dto.setSalario(pessoaEmprestimo.getPessoa().getSalario());
        dto.setCpf(pessoaEmprestimo.getPessoa().getCpf());
        dto.setEndereco(pessoaEmprestimo.getPessoa().getEndereco());
        dto.setAssinatura(pessoaEmprestimo.getAssinatura());
        return dto;
    }

}
