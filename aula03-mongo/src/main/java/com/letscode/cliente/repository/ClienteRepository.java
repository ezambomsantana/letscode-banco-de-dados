package com.letscode.cliente.repository;

import com.letscode.cliente.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Cliente findByCpf(String cpf);

    List<Cliente> findByNome(String nome);

}
