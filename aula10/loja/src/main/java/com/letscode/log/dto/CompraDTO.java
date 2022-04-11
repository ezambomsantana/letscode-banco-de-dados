package com.letscode.log.dto;

import com.letscode.log.model.Compra;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {

    private LocalDateTime dataCompra;
    private Float valorTotal;
    private String cpf;

    private List<ProdutoCompraDTO> produtos;

    public static CompraDTO convert(Compra compra) {
        return CompraDTO
                .builder()
                .dataCompra(compra.getDataCompra())
                .cpf(compra.getCpf())
                .valorTotal(compra.getValorTotal())
                .produtos(compra.getProdutoCompras().stream().map(ProdutoCompraDTO::convert).collect(Collectors.toList()))
                .build();
    }
}
