package com.ecommerceproject.projetoecommerce.usuarios;

import java.time.LocalDate;
import java.util.UUID;

public record FuncionarioResponseDTO(UUID id, String login, String email, String senha, Long codFuncionario, String nomeFuncionario, String CPF, String sexo, LocalDate dataNascimento, String CEP, String bairro, String telefone) {
    
    public FuncionarioResponseDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getLogin(), funcionario.getEmail(), funcionario.getSenha(), funcionario.getCodFuncionario(), funcionario.getNomeFuncionario(), funcionario.getCPF(), funcionario.getSexo(), funcionario.getDataNascimento(), funcionario.getCEP(), funcionario.getBairro(), funcionario.getTelefone());
    }

}
