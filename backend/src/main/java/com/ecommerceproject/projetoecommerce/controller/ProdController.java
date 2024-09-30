package com.ecommerceproject.projetoecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.projetoecommerce.domain.produto.Produto;
import com.ecommerceproject.projetoecommerce.domain.produto.ProdutoRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.produto.ProdutoResponseDTO;
import com.ecommerceproject.projetoecommerce.repositories.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public void saveProduto(@RequestBody ProdutoRequestDTO data) {

        Produto dadosProduto = new Produto(data);
        repository.save(dadosProduto);
    }

    @GetMapping
    public List<ProdutoResponseDTO> getAll() {
        List<ProdutoResponseDTO> listaProdutos = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();

        return listaProdutos;
    }

    @DeleteMapping("/{codProd}")
    public ResponseEntity<String>  deleteProduto(@PathVariable String codProd) {
        Optional<Produto> produto = repository.findById(codProd);

        if (produto.isPresent()) {
            repository.delete(produto.get());
            return ResponseEntity.ok("Produto deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codProd}")
    @Transactional
    public ResponseEntity<String> updateProduto(@PathVariable String codProd, @RequestBody ProdutoRequestDTO data) {
        Optional<Produto> optionalProduto = repository.findById(codProd);
    
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
    
            // Atualizando os dados do produto com os valores do request
            produto.setNome(data.nome());
            produto.setPreco(data.preco());
            produto.setQtdEstoque(data.qtdEstoque());
            produto.setCategoria(data.categoria());
            produto.setImagemUrl(data.imagemUrl());
    
            // Não é necessário chamar repository.save(produto) explicitamente,
            // pois o Hibernate gerencia automaticamente as alterações ao final da transação.
            return ResponseEntity.ok("Produto atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build(); // Caso o produto não seja encontrado
        }
    }

}
