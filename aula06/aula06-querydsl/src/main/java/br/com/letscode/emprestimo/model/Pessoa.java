package br.com.letscode.emprestimo.model;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "endereco")
    private String endereco;

    @ManyToMany(mappedBy = "pessoas", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos;


    public static Pessoa convert(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setSalario(dto.getSalario());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        return pessoa;
    }

}
