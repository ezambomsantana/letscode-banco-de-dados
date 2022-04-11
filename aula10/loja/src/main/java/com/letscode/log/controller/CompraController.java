package com.letscode.log.controller;

import com.letscode.log.dto.CompraDTO;
import com.letscode.log.model.Compra;
import com.letscode.log.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public CompraDTO salvarCompra(@RequestBody CompraDTO compra) {
        return compraService.salvarCompra(compra);
    }

    @GetMapping
    public List<CompraDTO> listCompras(@RequestParam(name = "cpf", required = false) String cpf, Pageable pageable) {
        return compraService.listCompras(cpf, pageable);
    }
}
