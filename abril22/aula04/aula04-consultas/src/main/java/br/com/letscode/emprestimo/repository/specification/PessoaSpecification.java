package br.com.letscode.emprestimo.repository.specification;

import br.com.letscode.emprestimo.model.Pessoa;
import org.springframework.data.jpa.domain.Specification;

public class PessoaSpecification {

    public static Specification<Pessoa> filterByName(String nome) {
        return (root, query, builder) ->
                builder.equal(root.get("nome"), nome);
    }

    public static Specification<Pessoa> filterByEndereco(String endereco) {
        return (root, query, builder) ->
                builder.equal(root.get("endereco"), endereco);
    }

    public static Specification<Pessoa> filterBySalarioGreaterThan(Double salario) {
        return (root, query, builder) ->
                builder.greaterThan(root.get("salario"), salario);
    }

}
