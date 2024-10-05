package com.ecommerceproject.projetoecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.pedido.ItenPedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItenPedido, String>{

}
