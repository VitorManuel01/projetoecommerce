package com.ecommerceproject.projetoecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

}
