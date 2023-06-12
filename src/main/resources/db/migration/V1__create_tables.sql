CREATE TABLE enderecos (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    numero INT,
    complemento VARCHAR(100)
);

CREATE TABLE colegios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    ativo BIT NOT NULL
);

CREATE TABLE unidades (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  endereco_id INT,
  colegio_id INT NOT NULL,
  ativo BIT NOT NULL,
  FOREIGN KEY (endereco_id) REFERENCES enderecos (id),
  FOREIGN KEY (colegio_id) REFERENCES colegios (id)
);

CREATE TABLE empresas (
  id SERIAL PRIMARY KEY,
  cnpj VARCHAR(15) NOT NULL,
  inscricaoEstadual VARCHAR(45),
  inscricaoMunicipal VARCHAR(45),
  razaoSocial VARCHAR(60) NOT NULL,
  nomeFantasia VARCHAR(60) NOT NULL,
  nomeUnidade VARCHAR(60) NOT NULL,
  endereco_id INT,
  ativo BIT NOT NULL,
  FOREIGN KEY (endereco_id) REFERENCES enderecos (id)
);

CREATE TABLE parentescos (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(10) NOT NULL
);

CREATE TABLE responsaveis (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  endereco_id INT,
  ativo BIT NOT NULL,
  FOREIGN KEY (endereco_id) REFERENCES enderecos (id)
);

CREATE TABLE tipos_contatos (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(20) NOT NULL
);

/*
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
*/