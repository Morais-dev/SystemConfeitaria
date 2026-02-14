CREATE DATABASE IF NOT EXISTS brigadeiros_tk;
USE brigadeiros_tk;

CREATE TABLE funcionario(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(15) NOT NULL,
    validacao VARCHAR(32) NOT NULL,
    cargo VARCHAR(15) NOT NULL
);

CREATE TABLE produto(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    valorUnt DECIMAL(9,2)
);

CREATE TABLE estoque(
	id INT AUTO_INCREMENT PRIMARY KEY,
    idProduto INT NOT NULL,
    sabor VARCHAR(30) NOT NULL,
    qtd INT NOT NULL,
    FOREIGN KEY (idProduto) REFERENCES produto(id)
);

CREATE TABLE vendas(
	id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    formaPagamento VARCHAR(8) NOT NULL,
    desconto DECIMAL(9,2) NOT NULL,
    valor DECIMAL(9,2) NOT NULL
);

CREATE TABLE produtoVenda(
	idVenda INT NOT NULL,
    idProduto INT NOT NULL,
    sabor VARCHAR(30) NOT NULL,
    qtd INT NOT NULL,
    PRIMARY KEY (idVenda, idProduto, sabor),
	FOREIGN KEY (idVenda) REFERENCES vendas(id),
    FOREIGN KEY (idProduto) REFERENCES produto(id)
);

CREATE TABLE gastos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    descricao VARCHAR(45) NOT NULL,
    qtd INT NOT NULL,
    valor DECIMAL(9.2) NOT NULL,
    formaPagamento VARCHAR(8) NOT NULL
);

INSERT INTO funcionario(nome, validacao, cargo) VALUES
('seu_funcionario', MD5('sua_senha'), 'chefe'),
('seu_funcionario', MD5('sua_senha'), 'chefe');