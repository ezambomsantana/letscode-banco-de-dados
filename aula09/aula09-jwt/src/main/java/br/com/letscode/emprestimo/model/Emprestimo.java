package br.com.letscode.emprestimo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "num_parcelas")
    private Integer numParcelas;

    @Column(name = "data_emprestimo")
    private LocalDateTime dataEmprestimo;

    @Column(name = "status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emprestimo", fetch = FetchType.LAZY)
    private List<Parcela> parcelas = new ArrayList<>();

    @OneToMany(mappedBy = "emprestimo")
    private List<PessoaEmprestimo> pessoas = new ArrayList<>();

}
