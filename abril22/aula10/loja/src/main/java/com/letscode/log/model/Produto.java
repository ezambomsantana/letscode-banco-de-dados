package com.letscode.log.model;

import com.letscode.log.dto.ProdutoDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private Float preco;
    private Integer quantidade;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoCompra> produtoCompras;

    public static Produto convert(ProdutoDTO dto) {
        return Produto
                .builder()
                .codigo(dto.getCodigo())
                .preco(dto.getPreco())
                .quantidade(dto.getQuantidade())
                .build();
    }
}
