package com.catalisa.gerenciadorestoque.repository;

import com.catalisa.gerenciadorestoque.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}