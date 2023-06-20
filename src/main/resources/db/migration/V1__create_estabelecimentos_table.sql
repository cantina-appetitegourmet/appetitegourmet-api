CREATE TABLE enderecos (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100)
);
INSERT INTO enderecos (cep, logradouro, bairro, cidade, uf, numero, complemento) VALUES ('51150-430', 'Rua José da Silva Lucena', 'Boa Viagem', 'Recife', 'PE', '20', NULL);

CREATE TABLE colegios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    endereco_id INTEGER REFERENCES enderecos(id),
    ativo BOOLEAN DEFAULT true
);
INSERT INTO colegios (nome) VALUES ('Colégio GGE');
INSERT INTO colegios (nome) VALUES ('Colégio CBV');
INSERT INTO colegios (nome) VALUES ('Colégio Equipe');
INSERT INTO colegios (nome) VALUES ('Colégio Diocesano');

CREATE TABLE cantinas_empresa (
  id SERIAL PRIMARY KEY,
  cnpj VARCHAR(18) NOT NULL UNIQUE,
  inscricaoEstadual VARCHAR(45) UNIQUE,
  inscricaoMunicipal VARCHAR(45) UNIQUE,
  razaoSocial VARCHAR(60) NOT NULL UNIQUE,
  nomeFantasia VARCHAR(60) NOT NULL,
  endereco_id INTEGER REFERENCES enderecos(id),
  ativo BOOLEAN DEFAULT true
);
INSERT INTO cantinas_empresa (cnpj, inscricaoEstadual, inscricaoMunicipal, razaoSocial, nomeFantasia, endereco_id) VALUES ('24.687.382/0001-31', '0670910-91', NULL, 'Mgd Servicos de Alimentacao LTDA', 'Appetite Gourmet', 1);

CREATE TABLE unidades (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  cantinaNome VARCHAR(60),
  colegio_id INTEGER REFERENCES colegios(id),
  cantina_empresa_id INTEGER REFERENCES cantinas_empresa(id),
  endereco_id INTEGER REFERENCES enderecos(id),
  ativo BOOLEAN DEFAULT true,
  UNIQUE (nome, colegio_id)
);
-- Inserindo as unidades do Colégio GGE
INSERT INTO unidades (nome, cantinaNome, colegio_id, cantina_empresa_id, endereco_id) VALUES ('Boa Viagem', 'Appetite Gourmet GGE Boa Viagem', 1, 1, 1);
INSERT INTO unidades (nome, colegio_id) VALUES ('Parnamirim', 1);
INSERT INTO unidades (nome, colegio_id) VALUES ('Benfica', 1);
INSERT INTO unidades (nome, colegio_id) VALUES ('Caruaru', 1);
-- Inserindo as unidades do Colégio CBV
INSERT INTO unidades (nome, colegio_id) VALUES ('Boa Viagem', 2);
INSERT INTO unidades (nome, colegio_id) VALUES ('Jaqueira', 2);
-- Inserindo as unidades do Colégio Equipe
INSERT INTO unidades (nome, colegio_id) VALUES ('Recife', 3);
-- Inserindo as unidades do Colégio Diocesano
INSERT INTO unidades (nome, colegio_id) VALUES ('Caruaru', 4);


CREATE TABLE series (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) UNIQUE NOT NULL,
    ativo BOOLEAN DEFAULT true
);
INSERT INTO series (nome) VALUES ('Educação Infantil');
INSERT INTO series (nome) VALUES ('Ensino Fundamental I');
INSERT INTO series (nome, ativo) VALUES ('Ensino Fundamental II', false);
INSERT INTO series (nome, ativo) VALUES ('Ensino Médio', false);

CREATE TABLE anos_serie (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    serie_id INTEGER REFERENCES series(id),
    UNIQUE (nome, serie_id)
);
INSERT INTO anos_serie (nome, serie_id) VALUES ('Infantil Berçário', 1);
INSERT INTO anos_serie (nome, serie_id) VALUES ('G1', 1);
INSERT INTO anos_serie (nome, serie_id) VALUES ('G2', 1);
INSERT INTO anos_serie (nome, serie_id) VALUES ('G3', 1);
INSERT INTO anos_serie (nome, serie_id) VALUES ('G4', 1);
INSERT INTO anos_serie (nome, serie_id) VALUES ('G5', 1);
INSERT INTO anos_serie (nome, serie_id) VALUES ('1° Ano', 2);
INSERT INTO anos_serie (nome, serie_id) VALUES ('2° Ano', 2);
INSERT INTO anos_serie (nome, serie_id) VALUES ('3° Ano', 2);
INSERT INTO anos_serie (nome, serie_id) VALUES ('4° Ano', 2);
INSERT INTO anos_serie (nome, serie_id) VALUES ('5° Ano', 2);
INSERT INTO anos_serie (nome, serie_id) VALUES ('6° Ano', 3);
INSERT INTO anos_serie (nome, serie_id) VALUES ('7° Ano', 3);
INSERT INTO anos_serie (nome, serie_id) VALUES ('8° Ano', 3);
INSERT INTO anos_serie (nome, serie_id) VALUES ('9° Ano', 3);
INSERT INTO anos_serie (nome, serie_id) VALUES ('1° Ano', 4);
INSERT INTO anos_serie (nome, serie_id) VALUES ('2° Ano', 4);
INSERT INTO anos_serie (nome, serie_id) VALUES ('3° Ano', 4);

CREATE TABLE anos_serie_unidade (
    id SERIAL PRIMARY KEY,
    ano_serie_id INTEGER REFERENCES anos_serie(id),
    unidade_id INTEGER REFERENCES unidades(id),
    UNIQUE (ano_serie_id, unidade_id)
);
INSERT INTO anos_serie_unidade (ano_serie_id, unidade_id)
SELECT anos_serie.id, unidades.id
FROM anos_serie, unidades;

CREATE TYPE turnos_enum AS ENUM (
  -- 'M' Manhã
  'M',
  -- 'T' Tarde
  'T',
  -- 'N' Noite
  'N'
);
CREATE TABLE ano_serie_unidade_turnos (
  id SERIAL PRIMARY KEY,
  ano_serie_unidade_id INTEGER REFERENCES anos_serie_unidade(id),
  turno turnos_enum,
  UNIQUE (ano_serie_unidade_id, turno)
);
INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, t
FROM anos_serie_unidade a
CROSS JOIN unnest(enum_range(null::turnos_enum)) t;


CREATE TYPE turmas_enum AS ENUM ('A', 'B', 'C', 'D', 'E', 'F');
CREATE TABLE turmas (
  id SERIAL PRIMARY KEY,
  ano_serie_unidade_turno_id INTEGER REFERENCES ano_serie_unidade_turnos(id),
  turma turmas_enum,
  UNIQUE (ano_serie_unidade_turno_id, turma)
);
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, te
FROM ano_serie_unidade_turnos ast
CROSS JOIN unnest(enum_range(NULL::turmas_enum)) AS te;

CREATE TABLE anos_letivos (
  id SERIAL PRIMARY KEY,
  ano VARCHAR(6) NOT NULL UNIQUE,
  ativo BOOLEAN DEFAULT true
);
INSERT INTO anos_letivos (ano) VALUES ('2023');
INSERT INTO anos_letivos (ano) VALUES ('2024');

CREATE TABLE turma_anos_letivos (
  id SERIAL PRIMARY KEY,
  turma_id INTEGER REFERENCES turmas(id),
  ano_letivo_id INTEGER REFERENCES anos_letivos(id),
  ativo BOOLEAN DEFAULT true,
  UNIQUE (turma_id, ano_letivo_id)
);
INSERT INTO turma_anos_letivos (turma_id, ano_letivo_id)
SELECT t.id, a.id
FROM turmas t, anos_letivos a;

CREATE TABLE planos_alimentares (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL UNIQUE,
  ativo BOOLEAN DEFAULT true
);
INSERT INTO planos_alimentares (nome) VALUES ('Lanche regular');
INSERT INTO planos_alimentares (nome) VALUES ('Lanche complementar');
INSERT INTO planos_alimentares (nome) VALUES ('Almoço');

CREATE TABLE planos_alimentares_precos (
  id SERIAL PRIMARY KEY,
  plano_alimentar_id INTEGER REFERENCES planos_alimentares(id),
  turma_anos_letivos_id INTEGER REFERENCES turma_anos_letivos(id),
  preco_dia DECIMAL(10, 2) NOT NULL,
  ativo BOOLEAN DEFAULT true,
  UNIQUE (plano_alimentar_id, turma_anos_letivos_id)
);
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) VALUES (1, 1, 55.00);
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) VALUES (1, 2, 55.50);