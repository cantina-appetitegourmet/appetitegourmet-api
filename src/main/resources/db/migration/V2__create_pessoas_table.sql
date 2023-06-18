CREATE TYPE sexos_enum AS ENUM ('M', 'F');

CREATE TABLE pessoas (
  id SERIAL PRIMARY KEY,
  nomeCompleto VARCHAR(100) NOT NULL,
  cpf VARCHAR(14),
  telefone VARCHAR(20),
  email VARCHAR(255),
  endereco_id INTEGER REFERENCES enderecos(id),
  sexo sexos_enum
);


CREATE TABLE graus_parentescos (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(20) UNIQUE NOT NULL
);
INSERT INTO graus_parentescos (nome) VALUES ('Pai');
INSERT INTO graus_parentescos (nome) VALUES ('Mãe');
INSERT INTO graus_parentescos (nome) VALUES ('Avô');
INSERT INTO graus_parentescos (nome) VALUES ('Avó');
INSERT INTO graus_parentescos (nome) VALUES ('Tio');
INSERT INTO graus_parentescos (nome) VALUES ('Tia');
INSERT INTO graus_parentescos (nome) VALUES ('Primo');
INSERT INTO graus_parentescos (nome) VALUES ('Prima');
INSERT INTO graus_parentescos (nome) VALUES ('Padrasto');
INSERT INTO graus_parentescos (nome) VALUES ('Madrasta');

CREATE TABLE alunos (
  id SERIAL PRIMARY KEY,
  pessoa_id INTEGER REFERENCES pessoas(id)
);
CREATE TABLE responsaveis (
  id SERIAL PRIMARY KEY,
  pessoa_id INTEGER REFERENCES pessoas(id)
);
CREATE TABLE responsaveis_alunos (
  id SERIAL PRIMARY KEY,
  aluno_id INTEGER REFERENCES alunos(id),
  responsavel_id INTEGER REFERENCES pessoas(id),
  grau_parentesco_id INTEGER REFERENCES graus_parentescos(id),
  ativo BOOLEAN NOT NULL DEFAULT TRUE,
  UNIQUE (responsavel_id, aluno_id)
);


CREATE TABLE alunos_turma_anos_letivos (
  id SERIAL PRIMARY KEY,
  aluno_id INTEGER REFERENCES alunos(id),
  turma_ano_letivo_id INTEGER REFERENCES turma_anos_letivos(id),
  UNIQUE (aluno_id, turma_ano_letivo_id)
);


CREATE TABLE nutricionistas (
  id SERIAL PRIMARY KEY,
  pessoa_id INTEGER REFERENCES pessoas(id),
  cantinas_empresa_id INTEGER REFERENCES cantinas_empresa(id)
);