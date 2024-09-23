package com.ecommerceproject.projetoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Cliente;
import com.ecommerceproject.projetoecommerce.domain.usuarios.ClienteRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.ClienteResponseDTO;
import com.ecommerceproject.projetoecommerce.repositories.ClienteRepository;


@RestController
@RequestMapping("cliente")
public class CliController {
    @Autowired
    private ClienteRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping //Anotação para realizar o Post e enviar os dados para o banco
    public void saveCliente(@RequestBody ClienteRequestDTO data){

        Cliente dadosCliente = new Cliente(data);

        repository.save(dadosCliente);
    
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ClienteResponseDTO> getAll(){

        List<ClienteResponseDTO> listaClientes = repository.findAll().stream().map(ClienteResponseDTO::new).toList();

        return listaClientes;
    }
}
