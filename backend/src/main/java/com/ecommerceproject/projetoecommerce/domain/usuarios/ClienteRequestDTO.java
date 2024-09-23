package com.ecommerceproject.projetoecommerce.domain.usuarios;

import java.time.LocalDate;

public record ClienteRequestDTO(String login, String email, String senha, Role funcao, String nomeCliente, String CPF, String sexo, LocalDate dataNascimento, String CEP, String bairro, String telefone) {
    
}
