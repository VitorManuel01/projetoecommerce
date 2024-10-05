package com.ecommerceproject.projetoecommerce.domain.pedido;

import java.math.BigDecimal;

import com.ecommerceproject.projetoecommerce.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="itenpedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class ItenPedido {

    @Id
    private String id;


    @PrePersist
    public void gerarIdItenPedido(){
        this.id = "ITPD-" + System.currentTimeMillis();
    }

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    @JsonIgnore
    private Carrinho carrinho;

    public BigDecimal getPrecoTotal(){
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal getPrecoUnitario() {
        if (this.precoUnitario == null) {
            this.precoUnitario = this.produto.getPreco(); // Garante que o precoUnitario seja inicializado
        }
        return this.precoUnitario;
    }


}
