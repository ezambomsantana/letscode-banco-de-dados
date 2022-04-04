package br.com.letscode.emprestimo.repository;

import br.com.letscode.emprestimo.model.Authority;
import br.com.letscode.emprestimo.model.AuthorityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityKey> {
}
