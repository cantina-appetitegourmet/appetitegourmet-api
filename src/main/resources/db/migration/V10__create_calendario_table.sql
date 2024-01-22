
CREATE TABLE estados (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(2) NOT NULL
);
INSERT INTO estados (nome) VALUES ('PE');

CREATE TABLE cidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    estado_id INTEGER REFERENCES estados(id)
);
INSERT INTO cidades (nome, estado_id) SELECT 'RECIFE', e.id FROM estados e WHERE e.nome = 'PE';
INSERT INTO cidades (nome, estado_id) SELECT 'CARUARU', e.id FROM estados e WHERE e.nome = 'PE';
INSERT INTO cidades (nome, estado_id) SELECT 'PETROLINA', e.id FROM estados e WHERE e.nome = 'PE';

CREATE TABLE cidades_anos_letivos (
    id SERIAL PRIMARY KEY,
    ano_letivo_id INTEGER REFERENCES anos_letivos(id),
    cidade_id INTEGER REFERENCES cidades(id),
    inicio_ano_letivo DATE  NOT NULL,
    fim_primeiro_semestre DATE  NOT NULL,
    inicio_segundo_semestre DATE  NOT NULL,
    fim_ano_letivo DATE  NOT NULL
);

-- RECIFE
INSERT INTO cidades_anos_letivos (ano_letivo_id, cidade_id,
                                  inicio_ano_letivo, fim_primeiro_semestre, 
                                  inicio_segundo_semestre, fim_ano_letivo) 
	SELECT al.id, cid.id, '2024-02-05', '2024-07-05', '2024-07-24', '2024-12-20'  
	FROM cidades cid, anos_letivos al WHERE c.nome = 'RECIFE' and al.ano = '2024';
	
-- CARUARU
INSERT INTO cidades_anos_letivos (ano_letivo_id, cidade_id,
                                  inicio_ano_letivo, fim_primeiro_semestre, 
                                  inicio_segundo_semestre, fim_ano_letivo) 
	SELECT al.id, cid.id, '2024-02-05', '2024-07-05', '2024-07-24', '2024-12-20'  
	FROM cidades cid, anos_letivos al WHERE c.nome = 'CARUARU' and al.ano = '2024';
	
-- PETROLINA
INSERT INTO cidades_anos_letivos (ano_letivo_id, cidade_id,
                                  inicio_ano_letivo, fim_primeiro_semestre, 
                                  inicio_segundo_semestre, fim_ano_letivo) 
	SELECT al.id, cid.id, '2024-02-05', '2024-07-05', '2024-07-24', '2024-12-20'  
	FROM cidades cid, anos_letivos al WHERE c.nome = 'PETROLINA' and al.ano = '2024';

CREATE TABLE calendarios_excecao (
    id SERIAL PRIMARY KEY,
    ano_letivo_id INTEGER REFERENCES anos_letivos(id),
    unidade_id INTEGER REFERENCES unidades(id),
    turma_id INTEGER REFERENCES turmas(id),
    serie_id INTEGER REFERENCES series(id),
    plano_alimentar_id INTEGER REFERENCES planos_alimentares(id),
    data DATE  NOT NULL,
    util BOOLEAN NOT NULL,
    observacao VARCHAR(300) NOT NULL
);

ALTER TABLE unidades
ADD cidade_id INTEGER REFERENCES cidades(id);

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('51150430','Rua José da Silva Lucena','Boa Viagem','Recife','PE','20',null);
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Boa Viagem','Appetite Gourmet GGE Boa Viagem',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio GGE' and can.cnpj = '24.687.382/0001-31' and cid.nome = 'RECIFE'

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('52060190','Rua Abraham Lincoln','Parnamirim','Recife','PE','83',null);	
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Parnamirim','Appetite Gourmet GGE Parnamirim',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio GGE' and can.cnpj = '40.208.467/0001-46' and cid.nome = 'RECIFE'

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('50720001','Rua Benfica','Madalena','Recife','PE','286',null);
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Benfica','Appetite Gourmet GGE Benfica',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio GGE' and can.cnpj = '48.893.826/0001-53' and cid.nome = 'RECIFE'

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('55016430','R. Professsor Azael Leitão','Maurício de Nassau','Caruaru','PE','8A',null);
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Caruaru','Appetite Gourmet GGE Caruaru',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio GGE' and can.cnpj = '28.319.352/0001-23' and cid.nome = 'CARUARU'

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('51020170','Rua Professor Eduardo Wanderley Filho','Boa Viagem','Recife','PE','539',null);
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Boa Viagem','Appetite Gourmet CBV Boa Viagem',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio CBV' and can.cnpj = '35.857.574/0001-65' and cid.nome = 'RECIFE'

INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('52041800','R. Dr. José Maria','Tamarineira','Recife','PE','1106',null);
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Jaqueira','Appetite Gourmet CBV Jaqueira',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio CBV' and can.cnpj = '35.857.206/0001-17' and cid.nome = 'RECIFE'
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('50610050','Rua Demóstenes Olinda','Madalena','Recife','PE','121',null);
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Torre','Appetite Gourmet Equipe',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio Equipe' and can.cnpj = '97.534.191/0001-03' and cid.nome = 'RECIFE'
	
INSERT INTO public.enderecos(
	cep, logradouro, bairro, cidade, uf, numero, complemento)
	VALUES ('56328020','AVENIDA CARDOSO DE SA','PEDRA DO BODE','PETROLINA','PE','0','AREA 03/04/05 GLEBA 02 CANTINA ESCOLA GGE');
INSERT INTO public.unidades(
	nome, cantinanome, tipo_desconto_boleto, valor_desconto_boleto, tipo_desconto_condicional_boleto, valor_desconto_condicional_boleto, valor_multa_boleto, valor_juros_dia_boleto, colegio_id, cantina_empresa_id, endereco_id, ativo, cidade_id)
	SELECT 'Petrolina','Appetite Gourmet GGE Petrolina',0,0.00,0,0.00,2.00,0.03,col.id, can.id, currval('enderecos_id_seq'),true,cid.id 
	from colegios col, cantinas_empresa can, cidades cid 
	where col.nome = 'Colégio GGE' and can.cnpj = '40.654.219/0001-29' and cid.nome = 'PETROLINA'


INSERT INTO anos_serie_unidade (ano_serie_id, unidade_id)
SELECT anos_serie.id, unidades.id
FROM anos_serie , unidades 
where unidades.id not in (select id from unidade where cantinanome = 'Appetite Gourmet GGE Benfica');

INSERT INTO anos_serie_unidade (ano_serie_id, unidade_id)
SELECT anos_serie.id, unidades.id
FROM anos_serie , unidades 
where anos_serie.id in (select id from anos_serie 
                        where serie_id in (select id from serie 
                                           where nome = 'Ensino Fundamental I')) 
      and 
      unidades.id in (select id from unidade 
                      where cantinanome = 'Appetite Gourmet GGE Benfica');



INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, 'M' FROM anos_serie_unidade a;
INSERT INTO ano_serie_unidade_turnos (ano_serie_unidade_id, turno)
SELECT a.id, 'T' FROM anos_serie_unidade a;


INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'A' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'B' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'C' FROM ano_serie_unidade_turnos ast;
INSERT INTO turmas (ano_serie_unidade_turno_id, turma)
SELECT ast.id, 'D' FROM ano_serie_unidade_turnos ast;



INSERT INTO turma_anos_letivos (turma_id, ano_letivo_id)
SELECT t.id, a.id
FROM turmas t, anos_letivos a;


------ GGE  

INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome <> 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome <> 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.50 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome = 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.50 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome = 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));
                                                  
--------- Almoço 

INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE pl.descricao = 'Combinações Almoços' 
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome <> 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE pl.descricao = 'Combinações Almoços' 
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome <> 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.50 from planos_alimentares pl, turma_anos_letivos tal 
WHERE pl.descricao = 'Combinações Almoços' 
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome = 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.50 from planos_alimentares pl, turma_anos_letivos tal 
WHERE pl.descricao = 'Combinações Almoços' 
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio GGE')
                                              and 
                                              cantinanome = 'Appetite Gourmet GGE Petrolina')
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));

------ CBV 

INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio CBV'))
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio CBV'))
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio CBV'))
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE (pl.descricao = 'Combinações Lanches Coletivos Regular' 
       or 
       pl.descricao = 'Combinações Lanches Coletivos Complementar')
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio CBV'))
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));
                                                  
------ EQUIPE  

INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 12.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE pl.descricao = 'Combinações Lanches Coletivos Regular'
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio Equipe'))
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Educação Infantil')))));
                                                  
INSERT INTO planos_alimentares_precos (plano_alimentar_id, turma_anos_letivos_id, preco_dia) 
SELECT pl.id, tal.id, 13.00 from planos_alimentares pl, turma_anos_letivos tal 
WHERE pl.descricao = 'Combinações Lanches Coletivos Regular'
      and 
      tal.turma_id 
      in (select id from turmas 
          where ano_serie_unidade_turno_id 
                in (select id from ano_serie_unidade_turnos 
                    where ano_serie_unidade_id 
                          in (select id from  ano_serie_unidade 
                              where unidade_id 
                                    in (select id from unidades 
                                        where colegio_id 
                                              in (select id from colegios 
                                                  where nome = 'Colégio Equipe'))
                                    and 
                                    ano_serie_id 
                                    in (select id from ano_serie 
                                        where serie_id 
                                              in (select id from serie 
                                                  where nome = 'Ensino Fundamental I')))));


CREATE TABLE feriados (
    id SERIAL PRIMARY KEY,
    cidade_id INTEGER REFERENCES cidades(id),
    estado_id INTEGER REFERENCES estados(id),
    data DATE  NOT NULL,
    observacao VARCHAR(300) NOT NULL
);

INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-01-01', 'Ano Novo' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-02-12', 'Carnaval' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-02-13', 'Carnaval' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-02-14', 'Carnaval' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, e.id, '2024-03-06', 'Revolucao Pernambucana' FROM estados e WHERE e.nome = 'PE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-03-29', 'Sexta-Feira Santa' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-04-21', 'Dia de Tiradentes' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-05-01', 'Dia do Trabalho' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-05-30', 'Corpus Christi' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, e.id, '2024-06-24', 'Dia de Sao Joao' FROM estados e WHERE e.nome = 'PE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-07-16', 'Padroeira' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-09-07', 'Independencia do Brasil' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-10-12', 'Nossa Senhora Aparecida' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-10-15', 'Dia do Professor' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-11-02', 'Dia de Finados' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-11-15', 'Proclamacao da Republica' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-12-08', 'Nossa Senhora da Conceicao' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, NULL, '2024-12-25', 'Natal' FROM cidades c WHERE c.nome = 'RECIFE';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-05-18', 'Aniversario da cidade' FROM cidades c WHERE c.nome = 'CARUARU';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-06-29', 'Sao Pedro' FROM cidades c WHERE c.nome = 'CARUARU';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-09-15', 'Padroeira Nossa Senhora das Dores' FROM cidades c WHERE c.nome = 'CARUARU';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-08-15', 'Nossa Senhora Rainha dos Anjos' FROM cidades c WHERE c.nome = 'PETROLINA';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-09-21', 'Aniversario da Cidade' FROM cidades c WHERE c.nome = 'PETROLINA';