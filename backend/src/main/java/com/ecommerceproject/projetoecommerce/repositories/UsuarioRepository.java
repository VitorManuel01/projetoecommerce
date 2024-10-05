package com.ecommerceproject.projetoecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findByEmail(String email);
}