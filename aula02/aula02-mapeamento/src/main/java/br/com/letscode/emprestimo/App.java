package br.com.letscode.emprestimo;

import br.com.letscode.emprestimo.model.Emprestimo;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.repository.EmprestimoRepository;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Bean
    public CommandLineRunner demo(PessoaRepository pessoaRepository, EmprestimoRepository emprestimoRepository) {
        return (args) -> {

            Pessoa pessoa = new Pessoa();
            pessoa.setCpf("12345674");
            pessoa.setNome("Eduardo");
            pessoa.setSalario(10000F);

            pessoaRepository.save(pessoa);


            List<Pessoa> pessoas = pessoaRepository.findAll();
            pessoas.forEach(p -> System.out.println(p.getNome()));

            List<Emprestimo> emprestimos = emprestimoRepository.findAll();
            emprestimos.forEach(e -> System.out.println(e.getNumParcelas()));



        };
    }
}