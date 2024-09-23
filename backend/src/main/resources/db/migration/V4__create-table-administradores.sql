CREATE TABLE administradores (
    id BINARY(16) PRIMARY KEY,  -- Referência à tabela usuarios
    admin BOOLEAN NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    sexo CHAR(1),
    data_nascimento DATE,
    cep VARCHAR(10),
    endereco VARCHAR(255),
    bairro VARCHAR(255),
    telefone VARCHAR(15),
    CONSTRAINT fk_usuario_admin FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);
