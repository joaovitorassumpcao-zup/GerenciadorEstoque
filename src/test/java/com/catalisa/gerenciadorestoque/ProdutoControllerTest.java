package com.catalisa.gerenciadorestoque.controller;

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
        MockitoAnnotations.initMocks(this);
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
        // TODO: Implementar o teste para listarProdutos
    }

    @Test
    void obterProdutoPorId() throws Exception {
        // TODO: Implementar o teste para obterProdutoPorId
    }

    @Test
    void atualizarProduto() throws Exception {
        // TODO: Implementar o teste para atualizarProduto
    }

    @Test
    void excluirProduto() throws Exception {
        // TODO: Implementar o teste para excluirProduto
    }
}
