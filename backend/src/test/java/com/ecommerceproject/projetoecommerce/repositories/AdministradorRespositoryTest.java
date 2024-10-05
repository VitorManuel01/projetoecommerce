package com.ecommerceproject.projetoecommerce.repositories;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Administrador;
import com.ecommerceproject.projetoecommerce.domain.usuarios.RegisterAdmDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Role;
import com.ecommerceproject.projetoecommerce.infra.security.SecurityFilter;
import com.ecommerceproject.projetoecommerce.infra.security.TokenService;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdministradorRespositoryTest {

    @Autowired
    private AdministradorRespository administradorRespository;

    @MockBean
    private TokenService tokenService;
    
    @MockBean
    private SecurityFilter securityFilter;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Deve retornar Administrador do banco de dados")
    void findByLoginCase1() {
        String login = "adminBolado";
        String email = "chorimano.vitor@hotmail.com";
        LocalDate dataNascimento = LocalDate.of(2001, 1, 30);
        RegisterAdmDTO data = new RegisterAdmDTO(login, email, "VitorSQLDeusVult", Role.ROLE_ADMIN, true, 
                                                  "Vitor Manuel", "10112019501", "M", dataNascimento,
                                                  "42702750", "rua das pedrinhas", "Centro", "71999133476");
        
        this.createAdministrador(data);
        UserDetails result = this.administradorRespository.findByLogin(login);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(login);
    }

    @Test
    @Transactional
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Não deve retornar Administrador do banco de dados se não existir")
    void findByLoginCase2() {
        String login = "adminBolado";

        UserDetails result = this.administradorRespository.findByLogin(login);

        assertThat(result).isNull();
    }

    @Transactional
    @WithMockUser(roles = "ADMIN")
    private Administrador createAdministrador(RegisterAdmDTO data) {
        Administrador newAdmin = new Administrador(data);
        return administradorRespository.save(newAdmin);
    }
}