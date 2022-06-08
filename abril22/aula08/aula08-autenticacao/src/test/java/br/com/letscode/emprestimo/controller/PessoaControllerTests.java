package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.PessoaDTO;
import br.com.letscode.emprestimo.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @WithMockUser
    @Test
    public void testPessoaPage() throws Exception {
        when(pessoaService.listAll(Mockito.any())).thenReturn(Page.empty());

        mockMvc.perform(
                get("/pessoa/page"))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    public void testCreatePessoa() throws Exception {
        when(pessoaService.criarPessoa(Mockito.any())).thenReturn(new PessoaDTO());

        mockMvc.perform(post("/pessoa/").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Eduardo\"}"))
                .andExpect(status().isOk());
    }


}
