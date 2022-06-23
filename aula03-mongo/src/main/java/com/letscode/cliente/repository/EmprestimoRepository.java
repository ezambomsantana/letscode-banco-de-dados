package com.letscode.cliente.repository;

import com.letscode.cliente.model.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
}
