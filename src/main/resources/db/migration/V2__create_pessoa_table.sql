CREATE TABLE pessoa (
  id SERIAL PRIMARY KEY,
  nome_completo VARCHAR(100) NOT NULL,
  sexo BOOLEAN,
  nascimento DATE,
  cpf VARCHAR(14),
  telefone VARCHAR(20),
  email VARCHAR(255),
  endereco_id INTEGER REFERENCES enderecos(id)
);


CREATE TABLE grau_parentesco (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(20) UNIQUE NOT NULL
);
INSERT INTO grau_parentesco (nome) VALUES ('Pai');
INSERT INTO grau_parentesco (nome) VALUES ('Mãe');
INSERT INTO grau_parentesco (nome) VALUES ('Avô');
INSERT INTO grau_parentesco (nome) VALUES ('Avó');
INSERT INTO grau_parentesco (nome) VALUES ('Tio');
INSERT INTO grau_parentesco (nome) VALUES ('Tia');
INSERT INTO grau_parentesco (nome) VALUES ('Primo');
INSERT INTO grau_parentesco (nome) VALUES ('Prima');
INSERT INTO grau_parentesco (nome) VALUES ('Padrasto');
INSERT INTO grau_parentesco (nome) VALUES ('Madrasta');

CREATE TABLE aluno (
  id SERIAL PRIMARY KEY,
  pessoa_id INTEGER REFERENCES pessoa(id)
);
CREATE TABLE responsavel (
  id SERIAL PRIMARY KEY,
  pessoa_id INTEGER REFERENCES pessoa(id)
);
CREATE TABLE responsavel_aluno (
  id SERIAL PRIMARY KEY,
  aluno_id INTEGER REFERENCES aluno(id),
  responsavel_id INTEGER REFERENCES pessoa(id),
  grau_parentesco_id INTEGER REFERENCES grau_parentesco(id),
  ativo BOOLEAN NOT NULL DEFAULT TRUE,
  UNIQUE (responsavel_id, aluno_id)
);


CREATE TABLE aluno_turma_anos_letivos (
  id SERIAL PRIMARY KEY,
  aluno_id INTEGER REFERENCES aluno(id),
  turma_ano_letivo_id INTEGER REFERENCES turma_anos_letivos(id),
  UNIQUE (aluno_id, turma_ano_letivo_id)
);


CREATE TABLE nutricionista (
  id SERIAL PRIMARY KEY,
  pessoa_id INTEGER REFERENCES pessoa(id),
  cantinas_empresa_id INTEGER REFERENCES cantinas_empresa(id)
);