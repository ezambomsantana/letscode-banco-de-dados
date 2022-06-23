package com.letscode.cliente.repository;

import com.letscode.cliente.model.Parcela;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParcelaRepository extends MongoRepository<Parcela, String> {

    List<Parcela> findByIdEmprestimo(String idEmprestimo);

    void deleteByIdEmprestimo(String id);
}
