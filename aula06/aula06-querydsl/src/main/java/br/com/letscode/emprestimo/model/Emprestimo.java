package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name="emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "num_parcelas")
    private Integer numParcelas;
    @Column(name = "data_emprestimo")
    private LocalDateTime dataEmprestimo;
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "emprestimo")
    private List<Parcela> parcelas;

    @ManyToMany
    @JoinTable(
            name = "pessoa_emprestimo",
            joinColumns = { @JoinColumn(name = "id_emprestimo") },
            inverseJoinColumns = { @JoinColumn(name = "id_pessoa") }
    )
    private List<Pessoa> pessoas;
}
