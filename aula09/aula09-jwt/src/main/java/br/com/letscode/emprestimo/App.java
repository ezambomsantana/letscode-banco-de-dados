package br.com.letscode.emprestimo;

import br.com.letscode.emprestimo.model.Authority;
import br.com.letscode.emprestimo.model.AuthorityKey;
import br.com.letscode.emprestimo.model.User;
import br.com.letscode.emprestimo.repository.AuthorityRepository;
import br.com.letscode.emprestimo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}