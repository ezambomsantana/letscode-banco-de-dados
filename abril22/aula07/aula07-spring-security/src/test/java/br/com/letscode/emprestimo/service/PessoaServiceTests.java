package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.model.Pessoa;
import br.com.letscode.emprestimo.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PessoaServiceTests {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void testListAllEmpty() {
        Pageable page = PageRequest.of(0, 5);

        Mockito.when(pessoaRepository.findAll(page)).thenReturn(Page.empty());
        Page<PessoaDTO> pessoas = pessoaService.listAll(page);

        Assertions.assertEquals(0, pessoas.getSize());
    }

    @Test
    public void testListAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(Pessoa.builder().nome("Eduardo").salario(5000F).cpf("123").build());
        pessoas.add(Pessoa.builder().nome("Joao").salario(10000F).cpf("321").build());
        Page<Pessoa> pagePessoas = new PageImpl<>(pessoas);

        Pageable page = PageRequest.of(0, 5);

        Mockito.when(pessoaRepository.findAll(page)).thenReturn(pagePessoas);

        Page<PessoaDTO> pessoasReturn = pessoaService.listAll(page);
        Assertions.assertEquals(2, pessoasReturn.getSize());
        Assertions.assertEquals("Eduardo", pessoasReturn.toList().get(0).getNome());
        Assertions.assertEquals(5000F, pessoasReturn.toList().get(0).getSalario());

        Mockito.verify(pessoaRepository, Mockito.times(1))
                .findAll(page);
    }

    @Test
    public void testCreatePessoa() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Eduardo");
        pessoaDTO.setCpf("1234");

        Mockito.when(pessoaRepository.save(Mockito.any()))
                .thenReturn(Pessoa.convert(pessoaDTO));

        PessoaDTO pessoaReturn = pessoaService.criarPessoa(pessoaDTO);
        Assertions.assertEquals("Eduardo", pessoaReturn.getNome());
        Assertions.assertEquals("1234", pessoaReturn.getCpf());
    }

    @Test
    public void testDelete() {

        Pessoa pessoa = Pessoa.builder().nome("Eduardo").salario(5000F).build();
        Mockito.when(pessoaRepository.findByCpf("123")).thenReturn(Optional.of(pessoa));
        pessoaService.deletePessoa("123");

        Mockito.verify(pessoaRepository, Mockito.times(1))
                .findByCpf("123");

        Mockito.verify(pessoaRepository, Mockito.times(1))
                .delete(Mockito.any());

    }

    @Test
    public void testDeleteThrow() {
        Mockito.when(pessoaRepository.findByCpf("123")).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class,
                () -> pessoaService.deletePessoa("123"));
    }


}
