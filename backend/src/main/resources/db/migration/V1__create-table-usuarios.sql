CREATE TABLE usuarios (
    id BINARY(16) PRIMARY KEY ,
    login VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    funcao VARCHAR(50)  -- Supondo que Role é um Enum e será armazenado como String
);