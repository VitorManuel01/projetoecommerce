package com.ecommerceproject.projetoecommerce.domain.usuarios;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="administradores")
@Table(name="administradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Administrador extends Usuario implements IsAdmin{
    private boolean admin;
    private String nome;
    private String CPF;
    private String sexo;
    private LocalDate dataNascimento;
    private String CEP;
    private String endereco;
    private String bairro;
    private String telefone;
    
    @Override
    public boolean isAdmin() {
        return this.admin;
    }


    public Administrador( String senha,boolean admin, String nome,  
                     String CPF, String sexo, LocalDate dataNascimento,String CEP, String endereco, String bairro, 
                       String telefone) {
    this.admin = admin;
    this.nome = nome;
    this.CPF = CPF;
    this.sexo = sexo;
    this.dataNascimento = dataNascimento;
    this.CEP = CEP;
    this.endereco = endereco;
    this.bairro = bairro;
    this.telefone = telefone;
}

    public Administrador( RegisterAdmDTO data) {
        super();
        this.setLogin(data.login());
        this.setEmail(data.email());
        this.setSenha(data.senha());
        this.admin = data.admin();
        this.nome = data.nome();
        this.CPF = data.CPF();
        this.sexo = data.sexo();
        this.dataNascimento = data.dataNascimento();
        this.CEP = data.CEP();
        this.endereco = data.endereco();
        this.bairro = data.bairro();
        this.telefone = data.telefone();
    }

}
