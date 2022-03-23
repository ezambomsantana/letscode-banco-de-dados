package br.com.letscode.emprestimo;

import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.repository.EmprestimoRepository;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import br.com.letscode.emprestimo.service.EmprestimoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Bean
    public CommandLineRunner demo(EmprestimoService emprestimoService) {
        return (args) -> {

            emprestimoService.buscaPessoa();


        };
    }
}