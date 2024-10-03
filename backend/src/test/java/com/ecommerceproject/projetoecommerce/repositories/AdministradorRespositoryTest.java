package com.ecommerceproject.projetoecommerce.repositories;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Administrador;
import com.ecommerceproject.projetoecommerce.domain.usuarios.RegisterAdmDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Role;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdministradorRespositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    AdministradorRespository administradorRespository;

    @Test
    @Transactional
    @DisplayName("Deve retornar Administrador do banco de dados")
    void findByLoginCase1() {
        String login = "adminBolado";
        String email = "chorimano.vitor@hotmail.com";
        LocalDate dataNascimento = LocalDate.of(2001, 1, 30);
        RegisterAdmDTO data= new RegisterAdmDTO(login, email, "VitorSQLDeusVult", Role.ROLE_ADMIN, true, "Vitor Manuel", "10112019501", "M", dataNascimento,"42702750", "rua das pedrinhas", "Centro", "71999133476" );
        this.createAdministrador(data);
        UserDetails result = this.administradorRespository.findByLogin(login);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(login);
    }

    @Test
    @Transactional
    @DisplayName("Não deve retornar Administrador do banco de dados se não existir")
    void findByLoginCase2() {
        String login = "adminBolado";

        UserDetails result = this.administradorRespository.findByLogin(login);

        assertThat(result).isNull();

    }
    @Transactional
    private Administrador createAdministrador(RegisterAdmDTO data){
        Administrador newAdmin = new Administrador(data);

        this.entityManager.persist(newAdmin);
        return newAdmin;
    }
}