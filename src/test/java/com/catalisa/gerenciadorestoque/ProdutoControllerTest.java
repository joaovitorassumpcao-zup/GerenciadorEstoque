package com.catalisa.gerenciadorestoque;

import com.catalisa.gerenciadorestoque.controller.ProdutoController;
import com.catalisa.gerenciadorestoque.dto.ProdutoDTO;
import com.catalisa.gerenciadorestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void criarProduto() throws Exception {
        ProdutoDTO produtoDTO = ProdutoDTO.builder()
                .nome("Produto Teste")
                .descricao("Descrição Teste")
                .preco(BigDecimal.valueOf(100.00))
                .quantidade(10)
                .build();

        when(produtoService.create(any(ProdutoDTO.class))).thenReturn(produtoDTO);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Produto Teste\", \"descricao\": \"Descrição Teste\", \"preco\": 100.0, \"quantidade\": 10 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Produto Teste"));

        verify(produtoService, times(1)).create(any(ProdutoDTO.class));
    }

    @Test
    void listarProdutos() throws Exception {
        ProdutoDTO produto1 = ProdutoDTO.builder()
                .nome("Produto 1")
                .descricao("Descrição 1")
                .preco(BigDecimal.valueOf(50.0))
                .quantidade(5)
                .build();

        ProdutoDTO produto2 = ProdutoDTO.builder()
                .nome("Produto 2")
                .descricao("Descrição 2")
                .preco(BigDecimal.valueOf(100.0))
                .quantidade(10)
                .build();

        when(produtoService.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[1].nome").value("Produto 2"));

        verify(produtoService, times(1)).findAll();
    }

    @Test
    void obterProdutoPorId() throws Exception {
        ProdutoDTO produto = ProdutoDTO.builder()
                .nome("Produto Teste")
                .descricao("Descrição Teste")
                .preco(BigDecimal.valueOf(100.0))
                .quantidade(10)
                .build();

        when(produtoService.findByID(1L)).thenReturn(Optional.of(produto));

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Teste"));

        verify(produtoService, times(1)).findByID(1L);
    }

    @Test
    void atualizarProduto() throws Exception {
        ProdutoDTO produtoAtualizado = ProdutoDTO.builder()
                .nome("Produto Atualizado")
                .descricao("Descrição Atualizada")
                .preco(BigDecimal.valueOf(150.0))
                .quantidade(15)
                .build();

        when(produtoService.update(eq(1L), any(ProdutoDTO.class))).thenReturn(Optional.of(produtoAtualizado));

        mockMvc.perform(put("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Produto Atualizado\", \"descricao\": \"Descrição Atualizada\", \"preco\": 150.0, \"quantidade\": 15 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Atualizado"));

        verify(produtoService, times(1)).update(eq(1L), any(ProdutoDTO.class));
    }

    @Test
    void excluirProduto() throws Exception {
        doNothing().when(produtoService).delete(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());

        verify(produtoService, times(1)).delete(1L);
    }
}
