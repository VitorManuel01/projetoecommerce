package com.ecommerceproject.projetoecommerce.domain.produto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="produtos")
@Table(name="produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="codProd")
public class Produto {

    @Id
    private String codProd;


    @PrePersist
    public void gerarCodProd(){
        this.codProd = "PROD-" + System.currentTimeMillis();
    }

    private String nome;
    private BigDecimal preco;
    private int qtdEstoque;
    private String categoria;
    private String imagemUrl;

    public Produto(ProdutoRequestDTO data) {
        this.categoria = data.categoria();
        this.imagemUrl = data.imagemUrl();
        this.nome = data.nome();
        this.preco = data.preco();
        this.qtdEstoque = data.qtdEstoque();
    }
}
