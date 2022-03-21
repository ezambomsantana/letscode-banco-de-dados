package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Float valor;
    @Column(name = "num_parcelas")
    private Integer numParcelas;
    @Column(name = "data_emprestimo")
    private LocalDateTime dataEmprestimo;
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "emprestimo")
    private List<Parcela> parcelas;



    private List<Pessoa> pessoas;
}
