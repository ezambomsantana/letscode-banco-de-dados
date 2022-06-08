package com.letscode.log.service;

import com.letscode.log.dto.ProdutoCompraDTO;
import com.letscode.log.dto.ProdutoDTO;
import com.letscode.log.model.Compra;
import com.letscode.log.model.Produto;
import com.letscode.log.model.ProdutoCompra;
import com.letscode.log.model.ProdutoCompraKey;
import com.letscode.log.repository.ProdutoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoCompraService {

    private final ProdutoCompraRepository produtoCompraRepository;
    private final ProdutoService produtoService;

    public List<ProdutoCompra> salvar(Compra compra, List<ProdutoCompraDTO> produtos) {
        List<ProdutoCompra> produtoCompras = new ArrayList<>();
        for (ProdutoCompraDTO dto : produtos) {
            Produto produto = produtoService.findByCodigo(dto.getCodigo());
            ProdutoCompra produtoCompra = ProdutoCompra.create(compra, produto, dto.getQuantidade());
            produtoCompras.add(produtoCompraRepository.save(produtoCompra));
        }
        return produtoCompras;
    }

}
