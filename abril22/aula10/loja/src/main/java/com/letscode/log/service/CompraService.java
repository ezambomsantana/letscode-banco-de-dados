package com.letscode.log.service;

import com.letscode.log.dto.CompraDTO;
import com.letscode.log.dto.ProdutoCompraDTO;
import com.letscode.log.dto.ProdutoDTO;
import com.letscode.log.model.Compra;
import com.letscode.log.model.Produto;
import com.letscode.log.model.ProdutoCompra;
import com.letscode.log.repository.CompraRepository;
import com.letscode.log.repository.specification.CompraSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final ProdutoService produtoService;
    private final ProdutoCompraService produtoCompraService;
    private final CompraRepository compraRepository;

    @Transactional
    public CompraDTO salvarCompra(CompraDTO dto) {
        List<Produto> produtos = new ArrayList<>();
        Float valorTotal = calculaValorTotal(dto);

        Compra compra = Compra.builder()
                .dataCompra(LocalDateTime.now())
                .valorTotal(valorTotal)
                .cpf(dto.getCpf())
                .build();

        compra = compraRepository.save(compra);
        compra.setProdutoCompras(produtoCompraService.salvar(compra, dto.getProdutos()));

        decrementaEstoque(dto.getProdutos());

        return CompraDTO.convert(compra);

    }

    private void decrementaEstoque(List<ProdutoCompraDTO> produtos) {
        for (ProdutoCompraDTO produtoCompraDTO : produtos) {
            produtoService.decrementaEstoque(produtoCompraDTO);
        }
    }

    private Float calculaValorTotal(CompraDTO dto) {
        Float valorTotal = 0F;
        for (ProdutoCompraDTO produtoDTO : dto.getProdutos()) {
            Produto produto = produtoService.findByCodigo(produtoDTO.getCodigo());
            valorTotal += produto.getPreco() * produtoDTO.getQuantidade();
        }
        return valorTotal;
    }

    public List<CompraDTO> listCompras(String cpf, Pageable pageable) {

        Specification<Compra> specification = Specification.where(null);
        if (cpf != null) {
            specification = specification.and(CompraSpecification.filterByCpf(cpf));
        }

        return compraRepository
                .findAll(specification, pageable)
                .stream()
                .map(CompraDTO::convert)
                .collect(Collectors.toList());

    }
}
