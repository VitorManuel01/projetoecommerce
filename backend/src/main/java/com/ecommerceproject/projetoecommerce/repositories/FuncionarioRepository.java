package com.ecommerceproject.projetoecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>{

}
