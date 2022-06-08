package com.letscode.log.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "produto_compra")
@Getter
@Setter
public class ProdutoCompra {

    @EmbeddedId
    private ProdutoCompraKey produtoCompraKey;

    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Integer quantidade;

    public static ProdutoCompra create(Compra compra, Produto produto, Integer quantidade) {
        ProdutoCompra produtoCompra = new ProdutoCompra();
        produtoCompra.setQuantidade(quantidade);
        produtoCompra.setCompra(compra);
        produtoCompra.setProduto(produto);

        ProdutoCompraKey key = new ProdutoCompraKey();
        key.setIdCompra(compra.getId());
        key.setIdProduto(produto.getId());

        produtoCompra.setProdutoCompraKey(key);

        return produtoCompra;
    }
}
