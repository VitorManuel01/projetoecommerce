/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ecommerceproject.projetoecommerce.usuarios;

import java.time.LocalDate;

public record  FuncionaioRequestDTO(String login, String email, String senha, String nomeFuncionario, String CPF, String sexo, LocalDate dataNascimento, String CEP, String bairro, String telefone) {

}
