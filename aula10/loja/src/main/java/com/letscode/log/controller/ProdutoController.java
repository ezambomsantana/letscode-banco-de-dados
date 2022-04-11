package com.letscode.log.controller;

import com.letscode.log.dto.CompraDTO;
import com.letscode.log.dto.ProdutoDTO;
import com.letscode.log.service.CompraService;
import com.letscode.log.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CompraService compraService;

    @PostMapping
    public ProdutoDTO salvarProduto(@RequestBody ProdutoDTO produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping
    public List<ProdutoDTO> listProdutos(@RequestParam(name = "codigo", required = false) String codigo, Pageable pageable) {
        return produtoService.listProdutos(codigo, pageable);
    }




}
