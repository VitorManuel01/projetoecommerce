package com.ecommerceproject.projetoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.projetoecommerce.domain.usuarios.FuncionaioRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Funcionario;
import com.ecommerceproject.projetoecommerce.domain.usuarios.FuncionarioResponseDTO;
import com.ecommerceproject.projetoecommerce.repositories.FuncionarioRepository;


@RestController //Anotação para definir o controller
@RequestMapping("funcionario") //Anotação para para "mapear qual tabela/classe" se está trabalhando
public class FunController {


    @Autowired
    private FuncionarioRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping //Anotação para realizar o Post e enviar os dados para o banco
    public void saveFuncionario(@RequestBody FuncionaioRequestDTO data){

        Funcionario dadosFuncionario = new Funcionario(data);

        repository.save(dadosFuncionario);
    
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FuncionarioResponseDTO> getAll(){

        List<FuncionarioResponseDTO> listaFuncionarios = repository.findAll().stream().map(FuncionarioResponseDTO::new).toList();

        return listaFuncionarios;
    }
}
