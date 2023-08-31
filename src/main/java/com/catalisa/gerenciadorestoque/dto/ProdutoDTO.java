package com.catalisa.gerenciadorestoque.dto;

import com.catalisa.gerenciadorestoque.entity.Produto;
import lombok.Builder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.catalisa.gerenciadorestoque.entity.Produto}
 */

@Builder
public record ProdutoDTO(Long id, String nome, String descricao, Integer quantidade,
                         BigDecimal preco) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public ProdutoDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPreco()
        );
    }

    @Contract(" -> new")
    public @NotNull Produto toProduto() {
        return new Produto(
                id,
                nome,
                descricao,
                quantidade,
                preco
        );
    }
}