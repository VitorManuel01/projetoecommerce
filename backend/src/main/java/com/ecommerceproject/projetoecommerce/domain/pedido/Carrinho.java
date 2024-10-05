package com.ecommerceproject.projetoecommerce.domain.pedido;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Carrinho {
    
    @Id
    private String id;

    @PrePersist
    public void gerarIdCarrinho() {
        this.id = "CAR-" + System.currentTimeMillis();
    }

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItenPedido> itensPedido = new ArrayList<>();

    // @ManyToOne
    // @JoinColumn(name = "cliente_id")
    //private Cliente cliente;

    @OneToOne(mappedBy = "carrinho") // Relacionamento bidirecional
    private Pedido pedido;

    // Método para adicionar item ao carrinho
    public void adicionarItem(ItenPedido item) {
        itensPedido.add(item);
        item.setCarrinho(this); // Define o carrinho no item
    }

}
