package br.com.letscode.emprestimo.specification;


import br.com.letscode.emprestimo.model.Pessoa;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

public class PessoaSpecification {

    public static Specification<Pessoa> filterByName(String nome) {
        return (root, query, builder) -> builder.equal(root.get("nome"), nome);
    }

    public static Specification<Pessoa> filterByEnredereco(String endereco) {
        return (root, query, builder) -> builder.equal(root.get("endereco"), endereco);
    }

}