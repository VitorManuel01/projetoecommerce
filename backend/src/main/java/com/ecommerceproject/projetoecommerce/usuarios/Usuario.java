package com.ecommerceproject.projetoecommerce.usuarios;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
@MappedSuperclass
@EqualsAndHashCode(of = "id")
public abstract class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String login;
    private String email;
    private String senha;
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    
}
