CREATE TABLE itenpedido (
    id VARCHAR(255) PRIMARY KEY,
    produto_id VARCHAR(255), 
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    preco_total DECIMAL(10, 2) NOT NULL,
    carrinho_id VARCHAR(255),
    CONSTRAINT fk_produto
        FOREIGN KEY (produto_id) 
        REFERENCES produtos(cod_prod), 
    CONSTRAINT fk_carrinho
        FOREIGN KEY (carrinho_id) 
        REFERENCES carrinho(id)
);