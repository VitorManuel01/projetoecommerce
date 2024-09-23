// package com.ecommerceproject.projetoecommerce.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.ecommerceproject.projetoecommerce.domain.usuarios.Administrador;
// import com.ecommerceproject.projetoecommerce.domain.usuarios.AdministradorRequestDTO;
// import com.ecommerceproject.projetoecommerce.domain.usuarios.AdministradorResponseDTO;
// import com.ecommerceproject.projetoecommerce.repositories.AdministradorRespository;

// @RestController //Anotação para definir o controller
// @RequestMapping("administrador") //Anotação para para "mapear qual tabela/classe" se está trabalhando
// public class AdminController {


//     @Autowired
//     private AdministradorRespository repository;

//     @CrossOrigin(origins = "*", allowedHeaders = "*")
//     @PostMapping //Anotação para realizar o Post e enviar os dados para o banco
//     public void saveAdministrador(@RequestBody AdministradorRequestDTO data){

//         Administrador dadosAdministrador = new Administrador(data);

//         repository.save(dadosAdministrador);
    
//     }

//     @CrossOrigin(origins = "*", allowedHeaders = "*")
//     @GetMapping
//     public List<AdministradorResponseDTO> getAll(){

//         List<AdministradorResponseDTO> listaAdministradores = repository.findAll().stream().map(AdministradorResponseDTO::new).toList();

//         return listaAdministradores;
//     }
// }