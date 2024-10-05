CREATE TABLE pedido (
    id VARCHAR(255) PRIMARY KEY,
    data_pedido TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL, -- Se você usar um Enum, talvez precise tratar isso como string
    carrinho_id VARCHAR(255) NOT NULL,
    cliente_id VARCHAR(36) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    endereco_entrega VARCHAR(255) NOT NULL,
    metodo_pagamento VARCHAR(255) NOT NULL,
    FOREIGN KEY (carrinho_id) REFERENCES carrinho(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);