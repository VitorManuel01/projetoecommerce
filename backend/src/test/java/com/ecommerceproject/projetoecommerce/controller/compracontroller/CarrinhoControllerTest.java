/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.ecommerceproject.projetoecommerce.controller.compracontroller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerceproject.projetoecommerce.config.MockMvcConfig;
import com.ecommerceproject.projetoecommerce.config.TestSecurityConfig;
import com.ecommerceproject.projetoecommerce.controller.CarrinhoController;
import com.ecommerceproject.projetoecommerce.domain.pedido.Carrinho;
import com.ecommerceproject.projetoecommerce.domain.pedido.DTO.ComprarProdutoRequestDTO;
import com.ecommerceproject.projetoecommerce.domain.pedido.ItenPedido;
import com.ecommerceproject.projetoecommerce.domain.produto.Produto;
import com.ecommerceproject.projetoecommerce.infra.security.TokenService;
import com.ecommerceproject.projetoecommerce.repositories.CarrinhoRepository;
import com.ecommerceproject.projetoecommerce.repositories.UsuarioRepository;
import com.ecommerceproject.projetoecommerce.services.compraservices.CarrinhoService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
@Import(MockMvcConfig.class)
@ActiveProfiles("test")
@WebMvcTest(CarrinhoController.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, CarrinhoController.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarrinhoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarrinhoService carrinhoService;

    @MockBean
    private CarrinhoRepository carrinhoRepository;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper; // Mover a inicialização para o @Autowired

    @BeforeEach
    public void setUp() {
        // Não é necessário inicializar mocks aqui, pois o @MockBean já faz isso
    }

    @Test
    @WithMockUser(roles = "CLIENTE")
    @DisplayName("Deve iniciar carrinho e adicionar produto a itenproduto e este ao carrinho")
    public void testeComprarProduto() throws Exception {
        // Criar os dados do DTO de compra
        ComprarProdutoRequestDTO requestDTO = new ComprarProdutoRequestDTO("PROD-151515155", 3);

        // Criar o carrinho de exemplo
        Carrinho carrinho = new Carrinho();
        carrinho.setId("CART-12345"); // Defina um ID para o carrinho para testes

        // Simula a criação do carrinho
        when(carrinhoService.criarCarrinho()).thenReturn(carrinho);
        when(carrinhoRepository.findById(carrinho.getId())).thenReturn(Optional.of(carrinho)); // Mock do repositório

        // Simula o comportamento de adicionar o item ao carrinho
        doNothing().when(carrinhoService).adicionarItemAoCarrinho(any(Carrinho.class), any(ComprarProdutoRequestDTO.class));

        // Primeiro, cria o carrinho
        mockMvc.perform(post("/carrinho")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carrinho.getId())); // Verifica se o carrinho foi criado corretamente

        // Agora realiza a requisição POST para comprar o produto
        mockMvc.perform(post("/carrinho/{cartId}/comprar", carrinho.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))) // Usa objectMapper autowired
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Item adicionado ao carrinho com sucesso."));

        // Verifica se o método foi chamado corretamente
        verify(carrinhoService).adicionarItemAoCarrinho(eq(carrinho), eq(requestDTO));
    }


    @Test
    @WithMockUser(roles = "CLIENTE")
    @DisplayName("Deve exibir que o produto não foi encontrado")
    public void testeComprarProdutoProdutoNaoEncontrado() throws Exception {
        // Simula um carrinho existente
        Carrinho carrinho = new Carrinho();
        ComprarProdutoRequestDTO requestDTO = new ComprarProdutoRequestDTO("PROD-151515155", 3);
        carrinho.setId("CART-12345");

        // Simula a criação do carrinho
        when(carrinhoService.criarCarrinho()).thenReturn(carrinho);
        when(carrinhoRepository.findById(carrinho.getId())).thenReturn(Optional.of(carrinho)); // Mock do repositório

        // Simula uma exceção ao adicionar o item (produto não encontrado)
        doThrow(new RuntimeException("Produto não encontrado")).when(carrinhoService).adicionarItemAoCarrinho(carrinho, requestDTO);

        // Primeiro, cria o carrinho
        mockMvc.perform(post("/carrinho")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carrinho.getId())); // Verifica se o carrinho foi criado corretamente

        // Faz a requisição POST e verifica a resposta
        mockMvc.perform(post("/carrinho/{cartId}/comprar", carrinho.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))) // Usa objectMapper autowired
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Não foi possível adicionar item ao carrinho.")));
    }


    @Test
    @WithMockUser(roles = "CLIENTE")
    @DisplayName("Deve retornar um carrinho com itens")
    void getCarrinho() throws Exception {
        // Cria um produto exemplo
        Produto produto = new Produto();
        produto.setCodProd("PROD-123456");
        produto.setNome("Produto Teste");
        produto.setPreco(BigDecimal.valueOf(99.99));
        produto.setQtdEstoque(10);
        produto.setCategoria("Categoria Teste");
        produto.setImagemUrl("http://exemplo.com/imagem.jpg");

        // Cria um item de pedido exemplo
        ItenPedido item = new ItenPedido();
        item.setProduto(produto);
        item.setQuantidade(2);

        // Cria um carrinho exemplo
        Carrinho carrinho = new Carrinho();
        carrinho.setId("CAR-12534");
        carrinho.setItensPedido(List.of(item));

        // Simula o comportamento do serviço
        when(carrinhoService.buscarCarrinhoPorId("CAR-12534")).thenReturn(Optional.of(carrinho));

        // Faz a requisição GET e verifica a resposta
        mockMvc.perform(get("/carrinho/CAR-12534")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("CAR-12534"))
                .andExpect(jsonPath("$.itensPedido[0].produto.codProd").value("PROD-123456"))
                .andExpect(jsonPath("$.itensPedido[0].produto.nome").value("Produto Teste"))
                .andExpect(jsonPath("$.itensPedido[0].quantidade").value(2));
    }
}
