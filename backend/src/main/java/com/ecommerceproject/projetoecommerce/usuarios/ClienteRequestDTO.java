package com.ecommerceproject.projetoecommerce.usuarios;

import java.time.LocalDate;

public record ClienteRequestDTO(String login, String email, String senha, String nomeCliente, String CPF, String sexo, LocalDate dataNascimento, String CEP, String bairro, String telefone) {
    
}
