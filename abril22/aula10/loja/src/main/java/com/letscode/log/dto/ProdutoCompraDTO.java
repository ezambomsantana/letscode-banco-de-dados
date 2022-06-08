package com.letscode.log.dto;

import com.letscode.log.model.ProdutoCompra;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProdutoCompraDTO {
    private String codigo;
    private Integer quantidade;

    public static ProdutoCompraDTO convert(ProdutoCompra p) {
        return ProdutoCompraDTO.builder()
                .codigo(p.getProduto().getCodigo())
                .quantidade(p.getQuantidade())
                .build();
    }

}
