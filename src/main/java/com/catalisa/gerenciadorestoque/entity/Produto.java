package com.catalisa.gerenciadorestoque.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Nome", nullable = false, length = 55)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nome;

    @Column(name = "Descricao")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String descricao;

    @Column(name = "Quantidade", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer quantidade;

    @Column(name = "Preco", nullable = false, precision = 19, scale = 2)
    @JdbcTypeCode(SqlTypes.DECIMAL)
    private BigDecimal preco;

}