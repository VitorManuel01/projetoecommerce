package com.ecommerceproject.projetoecommerce.usuarios;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "funcionarios")
@Table(name = "funcionarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Funcionario extends Usuario{

    private String nomeFuncionario;
    private String CPF;
    private String sexo;
    private LocalDate dataNascimento;
    private String CEP;
    private String bairro;
    private String telefone;


    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        this.CPF = cPF;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String cEP) {
        CEP = cEP;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Funcionario(FuncionaioRequestDTO data) {
        super();
        this.setEmail(data.email());
        this.setLogin(data.login());
        this.setSenha(data.senha());
        this.nomeFuncionario = data.nomeFuncionario();
        this.CPF = data.CPF();
        this.sexo = data.sexo();
        this.dataNascimento = data.dataNascimento();
        this.CEP = data.CEP();
        this.bairro = data.bairro();
        this.telefone = data.telefone();
    }

    
    
}
