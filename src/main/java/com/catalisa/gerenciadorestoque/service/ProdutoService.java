package com.catalisa.gerenciadorestoque.service;

import com.catalisa.gerenciadorestoque.dto.ProdutoDTO;
import com.catalisa.gerenciadorestoque.entity.Produto;
import com.catalisa.gerenciadorestoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> findAll() {
        return produtoRepository.findAll().stream()
                .map(ProdutoDTO::new)
                .toList();
    }

    public Optional<ProdutoDTO> findByID(Long id) {
        return produtoRepository.findById(id).map(ProdutoDTO::new);
    }

    public ProdutoDTO create(@NotNull ProdutoDTO produtoDTO) {
        return new ProdutoDTO(produtoRepository.save(produtoDTO.toProduto()));
    }

    public Optional<ProdutoDTO> update(Long id, ProdutoDTO produtoDTO) {
        Optional<ProdutoDTO> produtoDTOOptional = findByID(id);

        if (produtoDTOOptional.isPresent()) {
            Produto produto = produtoDTOOptional.get().toProduto();

            if (produtoDTO.nome() != null) produto.setNome(produtoDTO.nome());
            if (produtoDTO.descricao() != null) produto.setDescricao(produtoDTO.descricao());
            if (produtoDTO.quantidade() != null) produto.setQuantidade(produtoDTO.quantidade());
            if (produtoDTO.preco() != null) produto.setPreco(produtoDTO.preco());

            produtoRepository.save(produto);
            return Optional.of(new ProdutoDTO(produto));
        } else return Optional.empty();
    }

    public void delete(Long id) {
        if(!produtoRepository.existsById(id)) throw new EntityNotFoundException();
        produtoRepository.deleteById(id);
    }
}
