package com.catalisa.gerenciadorestoque.repository;

import com.catalisa.gerenciadorestoque.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}