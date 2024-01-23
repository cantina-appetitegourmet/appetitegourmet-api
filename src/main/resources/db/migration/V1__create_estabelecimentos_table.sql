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

CREATE TABLE colegios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    endereco_id INTEGER REFERENCES enderecos(id),
    ativo BOOLEAN DEFAULT true
);
INSERT INTO colegios (nome) VALUES ('Colégio GGE');
INSERT INTO colegios (nome) VALUES ('Colégio CBV');
INSERT INTO colegios (nome) VALUES ('Colégio Equipe');

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

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('51150430','Rua José da Silva Lucena','Boa Viagem','Recife','PE','20',null);
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '24.687.382/0001-31',null,null,'MGD SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_c50c0e9e2fa17f735c8c42ebd8f9ee6a63ba7ec3", 
		      "client_secret": "Client_Secret_923d430124c3e9df61133f456c5ad0da0057aa11", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-182393-MGD-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_0aac73c94b0a48b39b956536759f1590fcd863cd", 
		      "client_secret": "Client_Secret_1de07663725ee9015b6e9903e5759b1e6559db93", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-182393-MGD-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('50610030','RUA J A DA SILVEIRA','MADALENA','RECIFE','PE','199',null);	
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '97.534.191/0001-03',null,null,'RGM SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_65e013721b53cd40595a151ce463e8129af91b5f", 
		      "client_secret": "Client_Secret_8ce48dc2241307d9d60571c5e25090f66b79438a", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-182395-RGM-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_2c1f2069abbf0e9d503f40a0d3cccbf889ff20ee", 
		      "client_secret": "Client_Secret_66e20c09bf26828e1efa132d01046e2521af70a1", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-182395-RGM-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('51020170','RUA PROFESSOR EDUARDO WANDERLEY FILHO','BOA VIAGEM','RECIFE','PE','539',null);	
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '35.857.574/0001-65',null,null,'VM SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
		   '{ "client_id": "Client_Id_bae01d61b558f9d95e35fae644223a9bc7434ce2", 
		      "client_secret": "Client_Secret_88264e7dc39521e415c5ef0526c69903b3aed161", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-231355-VM-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_ad7c1c08d5ed5b040d9b3a70e4044468540a9c38", 
		      "client_secret": "Client_Secret_98fe65ca7c95af81831ebdcf55b0af4a6d583cd5", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-231355-VM-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('55016825','RUA JOSE CARLOS DE OLIVEIRA','UNIVERSITARIO','CARUARU','PE','00L01',null);
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '28.319.352/0001-23',null,null,'NASCIMENTO & DANTAS SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_deb33165d6e6a189691447c493bc533a6a5cbc2e", 
		      "client_secret": "Client_Secret_384f3d32903c7ea09a314f8f9f07a44c0d6a3145", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-182389-NASCIMENTO-E-DANTAS-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_15aa293bbb0cfcba0160f61329b0d88bf49306ad", 
		      "client_secret": "Client_Secret_c759521b7cf212568fff87925610f295dc116df6", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-182389-NASCIMENTO-E-DANTAS-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('52041800','RUA DOUTOR JOSE MARIA','TAMARINEIRA','RECIFE','PE','1106',null);
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '35.857.206/0001-17',null,null,'RGDANTAS SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_e9941680b9c5ad5e334e46589bd5df2f44ca3c98", 
		      "client_secret": "Client_Secret_bd0d9cc2df649191a3e4191f6ad38100973ca7de", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-231360-RGDANTAS-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_956dfa608891f42b7d75f1471c6660ba6ff52dca", 
		      "client_secret": "Client_Secret_9aa863ad503c24e8db5845c82783ded8a685b0f3", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-231360-RGDANTAS-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('50720001','RUA BENFICA','MADALENA','RECIFE','PE','286',null);
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '48.893.826/0001-53',null,null,'MAV SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_a0eace7c339580764c60bd28dcea5076e556942b", 
		      "client_secret": "Client_Secret_732ce93c21f5fd0bcbead0fc5352f128fed932fb", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-435865-MAV-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_5e98294b06ef4ac93e68bcb49da585800daefd16", 
		      "client_secret": "Client_Secret_f563e3d51a17966c15fe78568d2687ddf432776b", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-435865-MAV-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('52060145','RUA DESEMBARGADOR GOIS CAVALCANTE','PARNAMIRIM','RECIFE','PE','460',null);
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '40.208.467/0001-46',null,null,'MF SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_86724fe4878269e1322eddcb7ea5482810937b69", 
		      "client_secret": "Client_Secret_196303f83210815161bba5f1840e22e6627d0a10", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-289103-MF-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_7e7a4eea08abbc3489e71f0037a12d72e772b307", 
		      "client_secret": "Client_Secret_bed03d51c9451a70e92cecc311932392a819ba61", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-289103-MF-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('56328020','AVENIDA CARDOSO DE SA','PEDRA DO BODE','PETROLINA','PE','0','AREA 03/04/05 GLEBA 02 CANTINA ESCOLA GGE');
INSERT INTO public.cantinas_empresa(
	cnpj, inscricao_estadual, inscricao_municipal, razao_social, nome_fantasia, id_empresa_integracao_pix, dados_integracao_pix, chave_pix, id_empresa_integracao_boleto, dados_integracao_boleto, endereco_id, ativo)
	SELECT '40.654.219/0001-29',null,null,'MRVR SERVICOS DE ALIMENTACAO LTDA','Appetite Gourmet', 1,
	       '{ "client_id": "Client_Id_0c67f7e7d6779aa75b68a283cd0c1dd4ca809b06", 
		      "client_secret": "Client_Secret_871fe7d11dda3ab87e5717bb1bf80e1f957f1747", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-288349-MRVR-Certificado.p12", "sandbox": false, "debug": false }',
		   null, 1,
		   '{ "client_id": "Client_Id_bf3be07cc48069d96b8e2a9e65be9767d95693e6", 
		      "client_secret": "Client_Secret_ae41625f54030ea2a48075677bbfd36ccd96103c", 
			  "certificate": "./certs/pagamentos/gerencianet/producao-288349-MRVR-Certificado.p12", "sandbox": false, "debug": false }',
		   currval('enderecos_id_seq'), true;


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

CREATE TABLE series (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) UNIQUE NOT NULL,
    ativo BOOLEAN DEFAULT true
);

CREATE TABLE anos_serie (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    serie_id INTEGER REFERENCES series(id),
    UNIQUE (nome, serie_id)
);

INSERT INTO series (nome) VALUES ('Educação Infantil');

-- GGE/CBV
INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Infantil 1', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Infantil 2', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Infantil 3', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Infantil 4', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Infantil 5', series.id from series where series.nome = 'Educação Infantil';

-- Equipe

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Maternal', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Nível 1', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Nível 2', series.id from series where series.nome = 'Educação Infantil';

INSERT INTO anos_serie (nome, serie_id) 
SELECT 'Nível 3', series.id from series where series.nome = 'Educação Infantil';


INSERT INTO series (nome) VALUES ('Ensino Fundamental I');

INSERT INTO anos_serie (nome, serie_id) 
SELECT '1° Ano', series.id from series where series.nome = 'Ensino Fundamental I';

INSERT INTO anos_serie (nome, serie_id) 
SELECT '2° Ano', series.id from series where series.nome = 'Ensino Fundamental I';

INSERT INTO anos_serie (nome, serie_id) 
SELECT '3° Ano', series.id from series where series.nome = 'Ensino Fundamental I';

INSERT INTO anos_serie (nome, serie_id) 
SELECT '4° Ano', series.id from series where series.nome = 'Ensino Fundamental I';

INSERT INTO anos_serie (nome, serie_id) 
SELECT '5° Ano', series.id from series where series.nome = 'Ensino Fundamental I';

CREATE TABLE anos_serie_unidade (
    id SERIAL PRIMARY KEY,
    ano_serie_id INTEGER REFERENCES anos_serie(id),
    unidade_id INTEGER REFERENCES unidades(id),
    UNIQUE (ano_serie_id, unidade_id)
);

CREATE TABLE ano_serie_unidade_turnos (
  id SERIAL PRIMARY KEY,
  ano_serie_unidade_id INTEGER REFERENCES anos_serie_unidade(id),
  turno CHAR(1) NOT NULL,
  UNIQUE (ano_serie_unidade_id, turno)
);

CREATE TABLE turmas (
  id SERIAL PRIMARY KEY,
  ano_serie_unidade_turno_id INTEGER REFERENCES ano_serie_unidade_turnos(id),
  turma CHAR(1) NOT NULL,
  UNIQUE (ano_serie_unidade_turno_id, turma)
);

CREATE TABLE anos_letivos (
  id SERIAL PRIMARY KEY,
  ano VARCHAR(6) NOT NULL UNIQUE,
  ativo BOOLEAN DEFAULT true
);
INSERT INTO anos_letivos (ano) VALUES ('2024');

CREATE TABLE turma_anos_letivos (
  id SERIAL PRIMARY KEY,
  turma_id INTEGER REFERENCES turmas(id),
  ano_letivo_id INTEGER REFERENCES anos_letivos(id),
  ativo BOOLEAN DEFAULT true,
  UNIQUE (turma_id, ano_letivo_id)
);

CREATE TABLE planos_alimentares (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(60) NOT NULL UNIQUE,
  descritivo VARCHAR(300) NOT NULL,
  ativo BOOLEAN DEFAULT true
);
INSERT INTO planos_alimentares (descricao, descritivo) VALUES ('Combinações Lanches Coletivos Regular', '');
INSERT INTO planos_alimentares (descricao, descritivo) VALUES ('Combinações Lanches Coletivos Complementar', '');
INSERT INTO planos_alimentares (descricao, descritivo) VALUES ('Combinações Almoços', '');

CREATE TABLE planos_alimentares_precos (
  id SERIAL PRIMARY KEY,
  plano_alimentar_id INTEGER REFERENCES planos_alimentares(id),
  turma_anos_letivos_id INTEGER REFERENCES turma_anos_letivos(id),
  preco_dia DECIMAL(10, 2) NOT NULL,
  ativo BOOLEAN DEFAULT true,
  UNIQUE (plano_alimentar_id, turma_anos_letivos_id)
);
