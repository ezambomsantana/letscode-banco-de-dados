package com.letscode.log.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="data_compra")
    private LocalDateTime dataCompra;

    @Column(name = "valor_total")
    private Float valorTotal;

    private String cpf;

    @OneToMany(mappedBy = "compra")
    private List<ProdutoCompra> produtoCompras;

}
