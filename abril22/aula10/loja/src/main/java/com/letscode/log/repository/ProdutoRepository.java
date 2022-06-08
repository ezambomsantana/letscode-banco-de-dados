package com.letscode.log.repository;

import com.letscode.log.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {
    Optional<Produto> findByCodigo(String codigo);

    @Modifying
    @Query(value = "update produto set quantidade = quantidade - :dec where codigo = :codigo", nativeQuery = true)
    void updateProdutoEstoque(Integer dec, String codigo);
}
