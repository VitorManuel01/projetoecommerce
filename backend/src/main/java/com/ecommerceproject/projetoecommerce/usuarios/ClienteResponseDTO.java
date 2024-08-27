/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ecommerceproject.projetoecommerce.usuarios;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteResponseDTO(UUID id, String login, String email, String senha, Long codCliente, String nomeCliente, String CPF, String sexo, LocalDate dataNascimento, String CEP, String bairro, String telefone) {
    public ClienteResponseDTO(Cliente cliente){
        this(cliente.getId(), cliente.getLogin(), cliente.getEmail(), cliente.getSenha(), cliente.getCodCliente(), cliente.getNomeCliente(), cliente.getCPF(), cliente.getSexo(), cliente.getDataNascimento(), cliente.getCEP(), cliente.getBairro(), cliente.getTelefone());
    }
}
