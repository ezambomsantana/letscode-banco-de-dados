package com.letscode.respository;

import com.letscode.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCpf(String cpf);

}
