CREATE TABLE colegio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL
);

CREATE TABLE unidade (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  cep VARCHAR(8),
  logradouro VARCHAR(100),
  bairro VARCHAR(100),
  cidade VARCHAR(100),
  uf VARCHAR(2),
  numero INTEGER,
  complemento VARCHAR(100),
  colegio_id INT NOT NULL,
  FOREIGN KEY (colegio_id) REFERENCES colegio (id)
);

CREATE TABLE ano_letivo (
  id SERIAL PRIMARY KEY,
  ano INT NOT NULL
);

CREATE TABLE cardapio (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL,
  descritivo VARCHAR(300)
);

CREATE TABLE categoria_turma (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL
);

CREATE TABLE dependente (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cep VARCHAR(8),
  logradouro VARCHAR(100),
  bairro VARCHAR(100),
  cidade VARCHAR(100),
  uf VARCHAR(2),
  numero INT,
  complemento VARCHAR(100),
  data_nascimento DATE
);

CREATE TABLE desconto (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL,
  percentual DECIMAL(12, 2) NOT NULL,
  anoLetivo_id BIGINT NOT NULL,
  FOREIGN KEY (anoLetivo_id) REFERENCES ano_letivo(id)
);

CREATE TABLE meio_pagamento (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL
);

CREATE TABLE opcao (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL,
  observacao VARCHAR(300)
);

CREATE TABLE parentesco (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL
);

CREATE TABLE responsavel (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  cep VARCHAR(8),
  logradouro VARCHAR(100),
  bairro VARCHAR(100),
  cidade VARCHAR(100),
  uf VARCHAR(2),
  numero INT,
  complemento VARCHAR(100),
  email VARCHAR(300) NOT NULL,
  telefone VARCHAR(11)
);

CREATE TABLE restricao (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL
);

CREATE TABLE turma (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  categoriaTurma_id BIGINT NOT NULL,
  FOREIGN KEY (categoriaTurma_id) REFERENCES categoria_turma(id)
);