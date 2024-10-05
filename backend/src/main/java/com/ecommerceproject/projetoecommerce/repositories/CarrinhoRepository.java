package com.ecommerceproject.projetoecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.pedido.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, String>{

}
