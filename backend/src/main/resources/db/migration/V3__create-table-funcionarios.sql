CREATE TABLE funcionarios (
    id VARCHAR(36) PRIMARY KEY,  -- Referência à tabela usuarios
    nome_funcionario VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    sexo CHAR(1),
    data_nascimento DATE,
    cep VARCHAR(10),
    bairro VARCHAR(255),
    telefone VARCHAR(15),
    CONSTRAINT fk_usuario_funcionario FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);