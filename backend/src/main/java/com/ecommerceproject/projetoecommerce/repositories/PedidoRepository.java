package com.ecommerceproject.projetoecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String>{

}
