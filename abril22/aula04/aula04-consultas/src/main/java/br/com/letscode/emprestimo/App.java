package br.com.letscode.emprestimo;

import br.com.letscode.emprestimo.service.EmprestimoService;
import br.com.letscode.emprestimo.service.PessoaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
/*

    @Bean
    public CommandLineRunner demo(PessoaService pessoaService) {
        return (args) -> {

            pessoaService.specification();

        };
    }*/
}