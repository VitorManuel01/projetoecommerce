/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ecommerceproject.projetoecommerce.domain.usuarios;

import java.time.LocalDate;

public record  FuncionaioRequestDTO(String login, String email, String senha, Role funcao, String nomeFuncionario, String CPF, String sexo, LocalDate dataNascimento, String CEP, String bairro, String telefone) {

}
