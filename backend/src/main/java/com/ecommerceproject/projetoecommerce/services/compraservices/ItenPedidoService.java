package com.ecommerceproject.projetoecommerce.services.compraservices;

import org.springframework.stereotype.Service;

import com.ecommerceproject.projetoecommerce.domain.pedido.Carrinho;
import com.ecommerceproject.projetoecommerce.domain.pedido.ItenPedido;
import com.ecommerceproject.projetoecommerce.domain.produto.Produto;
@Service
public class ItenPedidoService {
    

    public ItenPedido criarItenPedido(Produto produto,Carrinho carrinho, int quantidade) {
        ItenPedido itenPedido = new ItenPedido();
        itenPedido.setProduto(produto);
        itenPedido.setCarrinho(carrinho);
        itenPedido.setQuantidade(quantidade);
        itenPedido.setPrecoUnitario(produto.getPreco());    
        return itenPedido;
    }

}
