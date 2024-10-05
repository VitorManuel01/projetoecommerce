package com.ecommerceproject.projetoecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.projetoecommerce.domain.pedido.Carrinho;
import com.ecommerceproject.projetoecommerce.domain.pedido.DTO.ComprarProdutoRequestDTO;
import com.ecommerceproject.projetoecommerce.repositories.CarrinhoRepository;
import com.ecommerceproject.projetoecommerce.services.compraservices.CarrinhoService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(CarrinhoController.class);

    @Autowired
    private CarrinhoRepository repository;

    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(HttpServletResponse response) {
        Carrinho carrinho = carrinhoService.criarCarrinho();

        if(carrinho !=null){
        // Adiciona o ID do carrinho no cookie
        Cookie cookie = new Cookie("cartId", carrinho.getId());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // Define o cookie por 1 dia
        response.addCookie(cookie);

        return ResponseEntity.ok(carrinho);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }

    }

    @PostMapping("/{cartId}/comprar")
    public ResponseEntity<String> comprarProduto(@RequestBody ComprarProdutoRequestDTO dataCompra, @PathVariable String cartId) {
        if (cartId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não encontrado seu fudido de merda, isso aqui que ta fudido.");
        }
        if(dataCompra == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Os dados não tão sendo passados seu merda seu fudido de merda, isso aqui que ta fudido.");
        }else{
            System.out.println(dataCompra);
        }
        Optional<Carrinho> carrinho = repository.findById(cartId);
        if (carrinho.isPresent()) {
            try {
                
                carrinhoService.adicionarItemAoCarrinho(carrinho.get(), dataCompra);
                return ResponseEntity.ok("Item adicionado ao carrinho com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível adicionar item ao carrinho.");

            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não encontrado.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> getCarrinho(@PathVariable String id) {
        Optional<Carrinho> carrinhoOpt = carrinhoService.buscarCarrinhoPorId(id);
        if (carrinhoOpt.isPresent()) {
            return ResponseEntity.ok(carrinhoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
