package com.ecommerceproject.projetoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Cliente;
import com.ecommerceproject.projetoecommerce.domain.usuarios.ClienteRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.ClienteResponseDTO;
import com.ecommerceproject.projetoecommerce.repositories.ClienteRepository;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Role;

@RestController
@RequestMapping("cliente")
public class CliController {
    @Autowired
    private ClienteRepository repository;

@PostMapping // Anotação para realizar o Post e enviar os dados para o banco
public ResponseEntity<Void> saveCliente(@RequestBody ClienteRequestDTO data) {
    // Criptografar a senha do cliente
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
    
    // Criar um novo Cliente com os dados fornecidos
    Cliente novoCliente = new Cliente();
    novoCliente.setLogin(data.login());
    novoCliente.setEmail(data.email());
    novoCliente.setSenha(encryptedPassword);
    novoCliente.setFuncao(Role.ROLE_CLIENTE); // Definir a senha criptografada
    novoCliente.setNomeCliente(data.nomeCliente());
    novoCliente.setCPF(data.CPF());
    novoCliente.setSexo(data.sexo());
    novoCliente.setDataNascimento(data.dataNascimento());
    novoCliente.setCEP(data.CEP());
    novoCliente.setBairro(data.bairro());
    novoCliente.setTelefone(data.telefone());

    // Salvar o cliente no repositório (tabela clientes e usuarios)
    repository.save(novoCliente);

    // Retornar uma resposta vazia com status 201 (Created)
    return ResponseEntity.status(HttpStatus.CREATED).build();
}

    @GetMapping
    public List<ClienteResponseDTO> getAll(){

        List<ClienteResponseDTO> listaClientes = repository.findAll().stream().map(ClienteResponseDTO::new).toList();

        return listaClientes;
    }
}
