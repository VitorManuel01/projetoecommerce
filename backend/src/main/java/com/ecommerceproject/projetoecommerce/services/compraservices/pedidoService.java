package com.ecommerceproject.projetoecommerce.services.compraservices;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.projetoecommerce.domain.pedido.Carrinho;
import com.ecommerceproject.projetoecommerce.domain.pedido.DTO.FinalizarPedidoDTO;
import com.ecommerceproject.projetoecommerce.domain.pedido.ItenPedido;
import com.ecommerceproject.projetoecommerce.domain.pedido.Pedido;
import com.ecommerceproject.projetoecommerce.domain.usuarios.Cliente;
import com.ecommerceproject.projetoecommerce.repositories.CarrinhoRepository;
import com.ecommerceproject.projetoecommerce.repositories.ClienteRepository;
import com.ecommerceproject.projetoecommerce.repositories.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class pedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    public Pedido fazerPedido(FinalizarPedidoDTO data) {
        Pedido pedido = new Pedido();

        pedido.setDataPedido(LocalDateTime.now());

        UUID idCliente = UUID.fromString(data.idCliente());
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isPresent()) {
            pedido.setCliente(clienteOpt.get());
            pedido.setEnderecoEntrega("CEP:" + clienteOpt.get().getCEP() + ", Bairro: " + clienteOpt.get().getBairro());
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com ID: " + idCliente);
        }

        Optional<Carrinho> carrinhoOpt = carrinhoService.buscarCarrinhoPorId(data.idCarrinho());
        if (carrinhoOpt.isPresent()) {
            pedido.setCarrinho(carrinhoOpt.get());
        } else {
            throw new EntityNotFoundException("Carrinho não encontrado com ID: " + data.idCarrinho());
        }

        List<ItenPedido> itensPedido = carrinhoOpt.get().getItensPedido();
        BigDecimal total = itensPedido.stream()
                .map(ItenPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setTotal(total);

        pedido.setMetodoPagamento(data.metodoPagamento());


        return repository.save(pedido);
    }
}
