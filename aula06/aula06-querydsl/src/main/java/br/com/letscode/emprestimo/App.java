package br.com.letscode.emprestimo;

import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String [] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(PessoaRepository pessoaRepository) {
        return (args) -> {

            List<Pessoa> pessoas = pessoaRepository.findAll();
            pessoas.forEach(p -> System.out.println(p.getNome() + " " + p.getCpf()));

            Pessoa pessoa = pessoaRepository.findById(1).orElseThrow(() -> new RuntimeException("Pessoa nao encontrada"));
            System.out.println(pessoa.getNome());


            Pessoa pNova = new Pessoa();
            pNova.setId(1);
            pNova.setNome("Luiz");
            pNova.setCpf("123476443");
            pNova.setSalario(BigDecimal.TEN);
            pNova.setEndereco("Rua aaaa");

            pessoaRepository.save(pNova);

        };
    }

}
