package com.letscode.log.repository.specification;

import com.letscode.log.model.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecification {

    public static Specification<Produto> filterByCodigo(String codigo) {
        return (root, query, builder) ->
                builder.equal(root.get("codigo"), codigo);
    }

}
