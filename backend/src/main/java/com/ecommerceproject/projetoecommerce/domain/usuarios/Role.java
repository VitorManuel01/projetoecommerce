package com.ecommerceproject.projetoecommerce.domain.usuarios;

public enum Role {
    ROLE_ADMIN("admin"),
    ROLE_CLIENTE("cliente"),
    ROLE_FUNCIONARIO("funcionario");

    private String funcao;

    Role(String funcao){
        this.funcao = funcao;
    }

    public String getRole() {
        return funcao;
    }

    public void setRole(String funcao) {
        this.funcao = funcao;
    }

    
}
    
