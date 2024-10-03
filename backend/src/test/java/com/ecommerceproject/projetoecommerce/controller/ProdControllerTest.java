package com.ecommerceproject.projetoecommerce.controller;

import com.ecommerceproject.projetoecommerce.domain.produto.Produto;
import com.ecommerceproject.projetoecommerce.domain.produto.ProdutoRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.produto.ProdutoResponseDTO;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Administrador;
import com.ecommerceproject.projetoecommerce.domain.usuarios.RegisterAdmDTO;
import com.ecommerceproject.projetoecommerce.repositories.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @MockBean
    private ProdutoRepository repository;

    @InjectMocks
    private ProdController prodController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Deve salvar um produto")
    void saveProduto() throws Exception{
        ProdutoRequestDTO requestDTO = new ProdutoRequestDTO("Produto Exemplo", BigDecimal.valueOf(99.99), 100, "Categoria Exemplo", "http://exemplo.com/imagem.jpg");

        Produto produto = new Produto(requestDTO);

        // Simula o comportamento do repositório
        when(repository.save(any(Produto.class))).thenReturn(produto);

        // faz o post

        mockMvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andDo(print())
                .andExpect(status().isOk());

        // Verifica se o método save foi chamado uma vez
        verify(repository, times(1)).save(any(Produto.class));
    }

@Test
@WithMockUser(roles = "ADMIN")
@DisplayName("Deve adicionar todos os produtos do banco à uma lista")
void getAll() throws Exception {
    List<Produto> produtos = List.of(
            new Produto("PROD-1727927606880","Produto 1", BigDecimal.valueOf(29.99), 50, "Categoria 1", "http://exemplo.com/imagem1.jpg"),
            new Produto("PROD-1727927713234","Produto 2", BigDecimal.valueOf(49.99), 30, "Categoria 2", "http://exemplo.com/imagem2.jpg")
    );


    when(repository.findAll()).thenReturn(produtos);

    mockMvc.perform(get("/produto")
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Produto 1")) // Verifica o nome do primeiro produto
            .andExpect(jsonPath("$[1].nome").value("Produto 2")); // Verifica o nome do segundo produto
}


    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Deve deletar um produto")
    void deleteProduto() throws Exception{
        String codProd = "PROD-1727927713234";
        Produto produto = new Produto(codProd,"Produto 2", BigDecimal.valueOf(49.99), 30, "Categoria 2", "http://exemplo.com/imagem2.jpg");
        when(repository.findById(codProd)).thenReturn(Optional.of(produto));
        mockMvc.perform(delete("/produto/" + codProd)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Produto deletado com sucesso!"));

        verify(repository, times(1)).delete(produto);
    }


@Test
@WithMockUser(roles = "ADMIN")
@DisplayName("Deve alterar um produto")
void updateProduto() throws Exception {
    String codProd = "PROD-1727927713234";
    Produto existingProduto = new Produto(codProd, "Produto 1", BigDecimal.valueOf(49.99), 30, "Categoria 1", "http://exemplo.com/imagem1.jpg");


    when(repository.findById(codProd)).thenReturn(Optional.of(existingProduto));

    // Dados que serão enviados na requisição de atualização
    ProdutoRequestDTO requestDTO = new ProdutoRequestDTO("Produto Atualizado", BigDecimal.valueOf(59.99), 20, "Categoria Atualizada", "http://exemplo.com/imagem_atualizada.jpg");

    mockMvc.perform(put("/produto/" + codProd)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDTO)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Produto atualizado com sucesso!")); // Verifica a mensagem de sucesso

    // Verifica se o método save não foi chamado explicitamente, já que o Hibernate deve gerenciar a transação
    verify(repository, never()).save(any(Produto.class));

    // Verifica se o produto foi atualizado corretamente
    verify(repository, times(1)).findById(codProd);
}

}