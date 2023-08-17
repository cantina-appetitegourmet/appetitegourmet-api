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
  inscricao_estadual VARCHAR(45) UNIQUE,
  inscricao_municipal VARCHAR(45) UNIQUE,
  razao_social VARCHAR(60) NOT NULL UNIQUE,
  nome_fantasia VARCHAR(60) NOT NULL,
  id_empresa_integracao_pix INTEGER NULL,
  dados_integracao_pix VARCHAR(1000) NULL,
  chave_pix VARCHAR(100) NULL,
  id_empresa_integracao_boleto INTEGER NULL,
  dados_integracao_boleto VARCHAR(1000) NULL,
  endereco_id INTEGER REFERENCES enderecos(id),
  ativo BOOLEAN DEFAULT true
);
INSERT INTO cantinas_empresa (cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id) VALUES ('24.687.382/0001-31', '0670910-91', NULL, 'Mgd Servicos de Alimentacao LTDA', 'Appetite Gourmet', 1, '{ "client_id": "Client_Id_51ded938f2204f162606c18cd1d6f1416084ae94", "client_secret": "Client_Secret_166e8b066f546f1d67a92400019e18b80b0797d7", "certificate": "./certs/pagamentos/gerencianet/producao-461469-cantina-prod.p12", "sandbox": false, "debug": false }', '8cf064ef-cba8-4328-91db-eb411897f34f', 1, '{ "client_id": "Client_Id_27fc608a43dff1bf4bc81549c5ba7d0cdc3c8c2b", "client_secret": "Client_Secret_20cc5ccd32ce678fcf78863d7808cd5147d60da8", "certificate": "./certs/pagamentos/gerencianet/producao-461469-cantina-prod.p12", "sandbox": false, "debug": false }', 1);

CREATE TABLE unidades (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  cantinaNome VARCHAR(60),
  tipo_desconto_boleto INTEGER NOT NULL,
  valor_desconto_boleto DECIMAL(10, 2) NOT NULL,
  tipo_desconto_condicional_boleto INTEGER NOT NULL,
  valor_desconto_condicional_boleto DECIMAL(10, 2) NOT NULL,
  valor_multa_boleto DECIMAL(10, 2) NOT NULL,
  valor_juros_dia_boleto DECIMAL(10, 2) NOT NULL,
  colegio_id INTEGER REFERENCES colegios(id),
  cantina_empresa_id INTEGER REFERENCES cantinas_empresa(id),
  endereco_id INTEGER REFERENCES enderecos(id),
  ativo BOOLEAN DEFAULT true,
  UNIQUE (nome, colegio_id)
);
-- Inserindo as unidades do Colégio GGE
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id) VALUES ('Boa Viagem', 'Appetite Gourmet GGE Boa Viagem', 2, 10.00, 2, 10.00, 2.00, 0.15, 1, 1, 1);
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Parnamirim', 'Appetite Gourmet GGE Parnamirim', 2, 10.00, 2, 10.00, 2.00, 0.15, 1);
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Benfica', 'Appetite Gourmet GGE Benfica', 2, 10.00, 2, 10.00, 2.00, 0.15, 1);
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Caruaru', 'Appetite Gourmet GGE Caruaru', 2, 10.00, 2, 10.00, 2.00, 0.15, 1);
-- Inserindo as unidades do Colégio CBV
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Boa Viagem', 'Appetite Gourmet CBV Boa Viagem', 2, 10.00, 2, 10.00, 2.00, 0.15, 2);
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Jaqueira', 'Appetite Gourmet CBV Jaqueira', 2, 10.00, 2, 10.00, 2.00, 0.15, 2);
-- Inserindo as unidades do Colégio Equipe
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Recife', 'Appetite Gourmet Equipe Recife', 2, 10.00, 2, 10.00, 2.00, 0.15, 3);
-- Inserindo as unidades do Colégio Diocesano
INSERT INTO unidades (nome, cantinaNome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id) VALUES ('Caruaru', 'Appetite Gourmet Diocesano Caruaru', 2, 10.00, 2, 10.00, 2.00, 0.15, 4);


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

/*
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
  turno CHAR(1) CHECK (turno IN ('M', 'T', 'N')) NOT NULL,
  UNIQUE (ano_serie_unidade_id, turno)
);
*/
CREATE TABLE ano_serie_unidade_turnos (
  id SERIAL PRIMARY KEY,
  ano_serie_unidade_id INTEGER REFERENCES anos_serie_unidade(id),
  turno CHAR(1) NOT NULL,
  UNIQUE (ano_serie_unidade_id, turno)
);
INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, 'M' FROM anos_serie_unidade a;
INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, 'T' FROM anos_serie_unidade a;
INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, 'N' FROM anos_serie_unidade a;
/*
INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, t
FROM anos_serie_unidade a
CROSS JOIN unnest(enum_range(null::turnos_enum)) t;
*/

/*
CREATE TYPE turmas_enum AS ENUM ('A', 'B', 'C', 'D', 'E', 'F');
*/
CREATE TABLE turmas (
  id SERIAL PRIMARY KEY,
  ano_serie_unidade_turno_id INTEGER REFERENCES ano_serie_unidade_turnos(id),
  turma CHAR(1) NOT NULL,
  UNIQUE (ano_serie_unidade_turno_id, turma)
);
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'A' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'B' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'C' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'D' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'E' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'F' FROM ano_serie_unidade_turnos ast;
/*
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, te
FROM ano_serie_unidade_turnos ast
CROSS JOIN unnest(enum_range(NULL::turmas_enum)) AS te;
*/

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
  descricao VARCHAR(60) NOT NULL UNIQUE,
  descritivo VARCHAR(300) NOT NULL,
  ativo BOOLEAN DEFAULT true
);
INSERT INTO planos_alimentares (descricao, descritivo) VALUES ('Lanche regular', 'Uma fruta, um suco');
INSERT INTO planos_alimentares (descricao, descritivo) VALUES ('Lanche complementar', 'Uma fruta, um suco');
INSERT INTO planos_alimentares (descricao, descritivo) VALUES ('Almoço', 'Uma proteina, um carboidrato, um suco');

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