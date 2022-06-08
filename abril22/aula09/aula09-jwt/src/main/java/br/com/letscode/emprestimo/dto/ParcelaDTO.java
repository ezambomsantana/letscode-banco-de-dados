package br.com.letscode.emprestimo.dto;

import br.com.letscode.emprestimo.model.Parcela;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParcelaDTO {

    private Float valor;
    private Integer num;
    private LocalDateTime dataPagamento;
    private String status;

    public static ParcelaDTO convert(Parcela parcela) {
        ParcelaDTO dto = new ParcelaDTO();
        dto.setValor(parcela.getValor());
        dto.setDataPagamento(parcela.getDataPagamento());
        dto.setNum(parcela.getNum());
        dto.setStatus(parcela.getStatus());
        return dto;
    }

}
