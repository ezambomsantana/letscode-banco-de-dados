package com.letscode.log.service;

import com.letscode.log.dto.ProdutoCompraDTO;
import com.letscode.log.dto.ProdutoDTO;
import com.letscode.log.model.Produto;
import com.letscode.log.repository.ProdutoRepository;
import com.letscode.log.repository.specification.ProdutoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoDTO salvarProduto(ProdutoDTO dto) {
        Produto produto = Produto.convert(dto);
        return ProdutoDTO.convert(produtoRepository.save(produto));
    }

    public List<ProdutoDTO> listProdutos(String codigo, Pageable pageable) {
        Specification<Produto> specification = Specification.where(null);
        if (codigo != null) {
            specification = specification.and(ProdutoSpecification.filterByCodigo(codigo));
        }

        return produtoRepository
                .findAll(specification, pageable)
                .stream()
                .map(ProdutoDTO::convert)
                .collect(Collectors.toList());

    }

    public Produto findByCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException());
    }

    public void decrementaEstoque(ProdutoCompraDTO dto) {
        produtoRepository.updateProdutoEstoque(dto.getQuantidade(), dto.getCodigo());
    }
}
