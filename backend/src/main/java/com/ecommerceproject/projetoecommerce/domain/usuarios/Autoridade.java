package com.ecommerceproject.projetoecommerce.domain.usuarios;

import org.springframework.security.core.GrantedAuthority;

public class Autoridade implements GrantedAuthority{

    private String autoridade;

    public Autoridade(String autoridade) {
        this.autoridade = autoridade;
    }

    

    @Override
    public String getAuthority() {
        return autoridade;
    }
    
}
