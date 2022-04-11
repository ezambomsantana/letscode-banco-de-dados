package com.letscode.log.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProdutoCompraKey implements Serializable {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_produto")
    private Integer idProduto;

}
