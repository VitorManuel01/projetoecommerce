CREATE TABLE clientes (
    id VARCHAR(36) PRIMARY KEY,  -- Referência à tabela usuarios
    nome_cliente VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    sexo CHAR(1),
    data_nascimento DATE,
    cep VARCHAR(10),
    bairro VARCHAR(255),
    telefone VARCHAR(15),
    CONSTRAINT fk_usuario_cliente FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);