package com.ecommerceproject.projetoecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Administrador;

@Repository
public interface AdministradorRespository extends JpaRepository<Administrador, UUID>{
    UserDetails findByLogin(String login);
    UserDetails findByEmail(String email);
}
