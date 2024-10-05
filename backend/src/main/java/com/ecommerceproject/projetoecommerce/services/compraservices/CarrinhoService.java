package com.ecommerceproject.projetoecommerce.services.compraservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.projetoecommerce.domain.pedido.Carrinho;
import com.ecommerceproject.projetoecommerce.domain.pedido.DTO.ComprarProdutoRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.pedido.ItenPedido;
import com.ecommerceproject.projetoecommerce.domain.produto.Produto;
import com.ecommerceproject.projetoecommerce.repositories.CarrinhoRepository;
import com.ecommerceproject.projetoecommerce.repositories.ItensPedidoRepository;
import com.ecommerceproject.projetoecommerce.repositories.ProdutoRepository;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository repository;

    @Autowired 
    private ItensPedidoRepository itensPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItenPedidoService itenPedidoService;


    public Carrinho criarCarrinho() {
        Carrinho carrinho = new Carrinho();
        // carrinho.setCliente(cliente); 
        repository.save(carrinho); // Salva o novo carrinho no banco de dados
        return carrinho;
    }

    public void adicionarItemAoCarrinho(Carrinho carrinho, ComprarProdutoRequestDTO dataCompraDTO) {
        Optional<Produto> produtoOpt = produtoRepository.findById(dataCompraDTO.codProd());
        
        if (produtoOpt.isEmpty()) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    
        Produto produtoEncontrado = produtoOpt.get();
        
        // No need to fetch carrinho again, we already have it
        ItenPedido itenPedido = itenPedidoService.criarItenPedido(produtoEncontrado, carrinho, dataCompraDTO.quantidade());
    
        // Adiciona o item ao carrinho
        carrinho.getItensPedido().add(itenPedido);
        itensPedidoRepository.save(itenPedido); // Salva o item no banco de dados
        repository.save(carrinho); // Salva o carrinho após adicionar o item
    }

    public Optional<Carrinho> buscarCarrinhoPorId(String id) {
        return repository.findById(id);
    }

}
