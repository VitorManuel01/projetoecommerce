package com.ecommerceproject.projetoecommerce.domain.usuarios;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ROLE_ADMIN("admin"),
    ROLE_CLIENTE("cliente"),
    ROLE_FUNCIONARIO("funcionario");

    private String funcao;

    Role(String funcao){
        this.funcao = funcao;
    }

    @JsonValue
    public String getFuncao() {
        return funcao;
    }

    @JsonCreator
    public static Role fromFuncao(String funcao) {
        for (Role role : Role.values()) {
            if (role.funcao.equalsIgnoreCase(funcao)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Função inválida: " + funcao);
    }

    public void setRole(String funcao) {
        this.funcao = funcao;
    }

    
}
    
