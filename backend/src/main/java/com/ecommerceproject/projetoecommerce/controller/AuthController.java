package com.ecommerceproject.projetoecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
import com.ecommerceproject.projetoecommerce.domain.usuarios.Usuario;
import com.ecommerceproject.projetoecommerce.infra.security.TokenService;
import com.ecommerceproject.projetoecommerce.repositories.AdministradorRespository;

@RestController
@RequestMapping("auth")
//@CrossOrigin(origins = "http://localhost:5173",  allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdministradorRespository admRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        try {
            if (data != null) {
                System.out.println("Existe dados");
                System.out.println("Email/Login: " + data.emailOrLogin() + ", Password: " + data.senha());
            } else {
                System.out.println("Não existe dados");
            }
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.emailOrLogin(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            System.out.println("Authentication successful: " + auth.isAuthenticated());
            System.out.println("User details: " + auth.getPrincipal());
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            // System.out.println("Login from token: " );
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }

    @PostMapping("/registerADM")
    public ResponseEntity<String>  registerADM(@RequestBody @Validated RegisterAdmDTO dataAdm) {
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
