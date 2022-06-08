package com.letscode.log.repository;

import com.letscode.log.model.ProdutoCompra;
import com.letscode.log.model.ProdutoCompraKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoCompraRepository extends JpaRepository<ProdutoCompra, ProdutoCompraKey> {



}
