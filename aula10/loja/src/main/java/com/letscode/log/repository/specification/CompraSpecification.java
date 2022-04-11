package com.letscode.log.repository.specification;

import com.letscode.log.model.Compra;
import com.letscode.log.model.Produto;
import org.springframework.data.jpa.domain.Specification;

public class CompraSpecification {

    public static Specification<Compra> filterByCpf(String cpf) {
        return (root, query, builder) ->
                builder.equal(root.get("cpf"), cpf);
    }

}
