/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ecommerceproject.projetoecommerce.domain.produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO (String codProd, String nome, BigDecimal preco, int qtdEstoque, String categoria, String imagemUrl) {

    public ProdutoResponseDTO(Produto produto) {
        this(produto.getCodProd(), produto.getNome(), produto.getPreco(), produto.getQtdEstoque(), produto.getCategoria(), produto.getImagemUrl());
        
    }
    
}
