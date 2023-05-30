CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_colegio INTEGER NOT NULL,
    FOREIGN KEY (id_colegio) REFERENCES colegio (id)
);