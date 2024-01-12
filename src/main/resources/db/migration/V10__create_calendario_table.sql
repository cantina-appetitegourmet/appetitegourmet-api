
CREATE TABLE estados (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(2) NOT NULL
);
INSERT INTO estados (nome) VALUES ('PE');
INSERT INTO estados (nome) VALUES ('PB');

CREATE TABLE cidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    estado_id INTEGER REFERENCES estados(id)
);
INSERT INTO cidades (nome, estado_id) SELECT 'RECIFE', e.id FROM estados e WHERE e.nome = 'PE';
INSERT INTO cidades (nome, estado_id) SELECT 'CARUARU', e.id FROM estados e WHERE e.nome = 'PE';
INSERT INTO cidades (nome, estado_id) SELECT 'PETROLINA', e.id FROM estados e WHERE e.nome = 'PE';
INSERT INTO cidades (nome, estado_id) SELECT 'JOAO PESSOA', e.id FROM estados e WHERE e.nome = 'PB';

CREATE TABLE cidades_anos_letivos (
    id SERIAL PRIMARY KEY,
    ano_letivo_id INTEGER REFERENCES anos_letivos(id),
    cidade_id INTEGER REFERENCES cidades(id),
    inicio_ano_letivo DATE  NOT NULL,
    fim_primeiro_semestre DATE  NOT NULL,
    inicio_segundo_semestre DATE  NOT NULL,
    fim_ano_letivo DATE  NOT NULL
);

CREATE TABLE calendarios (
    id SERIAL PRIMARY KEY,
    ano_letivo_id INTEGER REFERENCES anos_letivos(id),
    cidade_id INTEGER REFERENCES cidades(id),
    data DATE  NOT NULL,
    util BOOLEAN NOT NULL,
    observacao VARCHAR(300) NOT NULL
);


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
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, e.id, '2024-03-06', 'Revolucaoo Pernambucana' FROM estados e WHERE e.nome = 'PE';
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
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-06-24', 'Dia de Sao Joao' FROM cidades c WHERE c.nome = 'JOAO PESSOA';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT NULL, e.id, '2024-08-05', 'Fundacao do Estado da Paraiba' FROM estados e WHERE e.nome = 'PB';
INSERT INTO feriados(cidade_id, estado_id, data, observacao) SELECT c.id, c.estado_id, '2024-12-08', 'Nossa Senhora da Conceicao' FROM cidades c WHERE c.nome = 'JOAO PESSOA';
