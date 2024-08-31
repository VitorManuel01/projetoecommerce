CREATE TABLE clientes (
    id BINARY(16) NOT NULL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome_cliente VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    sexo CHAR(1),
    data_nascimento DATE,
    cep VARCHAR(10),
    bairro VARCHAR(255),
    telefone VARCHAR(20)
);
