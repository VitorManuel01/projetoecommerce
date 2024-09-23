package com.ecommerceproject.projetoecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Administrador;
import com.ecommerceproject.projetoecommerce.domain.usuarios.AuthenticationDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.LoginResponseDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.RegisterAdmDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.RegisterUserDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Usuario;
import com.ecommerceproject.projetoecommerce.infra.security.TokenService;
import com.ecommerceproject.projetoecommerce.repositories.AdministradorRespository;
import com.ecommerceproject.projetoecommerce.repositories.UsuarioRepository;

@RestController
@RequestMapping("auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdministradorRespository admRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.emailOrLogin(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);


        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/registerADM")
    public ResponseEntity registerADM(@RequestBody @Validated RegisterAdmDTO dataAdm) {
        if (this.admRepository.findByEmail(dataAdm.email()) != null) {
            return ResponseEntity.badRequest().build();
        } else if (this.admRepository.findByLogin(dataAdm.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
    
        String encryptedPassword = new BCryptPasswordEncoder().encode(dataAdm.senha());
    
        // Criar um novo Administrador, que é um Usuario
        Administrador novoAdministrador = new Administrador();
        novoAdministrador.setLogin(dataAdm.login());
        novoAdministrador.setEmail(dataAdm.email());
        novoAdministrador.setSenha(encryptedPassword);
        novoAdministrador.setFuncao(dataAdm.funcao());
        novoAdministrador.setAdmin(dataAdm.admin());
        novoAdministrador.setNome(dataAdm.nome());
        novoAdministrador.setCPF(dataAdm.CPF());
        novoAdministrador.setSexo(dataAdm.sexo());
        novoAdministrador.setDataNascimento(dataAdm.dataNascimento());
        novoAdministrador.setCEP(dataAdm.CEP());
        novoAdministrador.setEndereco(dataAdm.endereco());
        novoAdministrador.setBairro(dataAdm.bairro());
        novoAdministrador.setTelefone(dataAdm.telefone());
    
        // Salvar o novo Administrador (que também será salvo na tabela usuarios)
        this.admRepository.save(novoAdministrador);
    
        return ResponseEntity.ok().build();
    }
}
