package com.ecommerceproject.projetoecommerce.domain.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="pedido")
@Entity(name="pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Pedido {
    @Id
    private String id;


    @PrePersist
    public void gerarIdPedido(){
        this.id = "PDO-" + System.currentTimeMillis();
    }

    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToOne // Alterado para @OneToOne para refletir a relação correta
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;
    
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    private BigDecimal total;
    private String enderecoEntrega;
    private String metodoPagamento;
    

}
