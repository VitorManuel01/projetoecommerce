CREATE TABLE produtos (
    cod_prod VARCHAR(255) PRIMARY KEY,  -- Chave primária com o código do produto gerado
    nome VARCHAR(255) NOT NULL,         -- Nome do produto
    preco DECIMAL(10, 2) NOT NULL,      -- Preço com precisão de 2 casas decimais
    qtd_estoque INT NOT NULL,           -- Quantidade de estoque
    categoria VARCHAR(255),             -- Categoria do produto
    imagem_url VARCHAR(255)             -- URL da imagem do produto
);