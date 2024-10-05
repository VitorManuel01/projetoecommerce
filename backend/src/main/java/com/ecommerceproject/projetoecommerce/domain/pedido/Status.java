package com.ecommerceproject.projetoecommerce.domain.pedido;

public enum Status {
    PENDENTE("pendente"),           // Pedido criado, aguardando processamento
    CONFIRMADO("confirmado"),         // Pagamento aprovado, pedido confirmado
    PROCESSANDO("processando"),        // Pedido em processo de separação ou preparação
    ENVIADO("enviado"),            // Pedido enviado para o cliente
    ENTREGUE("entregue"),           // Pedido entregue ao cliente
    CANCELADO("cancelado"),          // Pedido cancelado
    DEVOLVIDO("devolvido"),          // Pedido devolvido pelo cliente
    FALHA_PAGAMENTO("falha_no_pagamento"),    // Falha na tentativa de pagamento
    AGUARDANDO_PAGAMENTO("aguardando_pagamento"); // Aguardando recebimento do pagamento

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


}
