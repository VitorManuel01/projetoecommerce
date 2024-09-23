package com.ecommerceproject.projetoecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    

    private ProdutoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduto(@RequestBody ProdutoRequestDTO data){

        Produto dadosProduto = new Produto(data);
        repository.save(dadosProduto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProdutoResponseDTO> getAll(){
        List<ProdutoResponseDTO> listaProdutos = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();

        return listaProdutos;
    }
}