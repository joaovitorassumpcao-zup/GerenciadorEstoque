package com.catalisa.gerenciadorestoque.controller;

import com.catalisa.gerenciadorestoque.dto.ProdutoDTO;
import com.catalisa.gerenciadorestoque.service.ProdutoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /// LISTAR PRODUTOS ///

    @Operation(summary = "Lista todos os produtos disponíveis no estoque")
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        List<ProdutoDTO> produtos = produtoService.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    /// OBTER PRODUTO POR ID ///

    @Operation(summary = "Obtém detalhes de um produto específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Produto não encontrado") })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterProdutoPorId(@PathVariable Long id) {
        return produtoService.findByID(id)
                .map(produto -> new ResponseEntity<>(produto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /// CRIAR PRODUTO ///

    @Operation(summary = "Cria um novo produto no estoque")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Produto criado com sucesso"),
        @ApiResponse(code = 400, message = "Entrada inválida") })
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO novoProduto = produtoService.create(produtoDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    /// ATUALIZAR PRODUTO ///

    @Operation(summary = "Atualiza um produto existente no estoque pelo ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produto atualizado com sucesso"),
        @ApiResponse(code = 404, message = "Produto não encontrado"),
        @ApiResponse(code = 400, message = "Entrada inválida") })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.update(id, produtoDTO)
                .map(produtoAtualizado -> new ResponseEntity<>(produtoAtualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /// DELETAR PRODUTO ///

    @Operation(summary = "Exclui um produto do estoque pelo ID")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Produto excluído com sucesso"),
        @ApiResponse(code = 404, message = "Produto não encontrado") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
