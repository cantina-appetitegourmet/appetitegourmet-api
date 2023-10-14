CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(300) NOT NULL,
    email VARCHAR(300) NOT NULL,
    password VARCHAR(120) NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO roles (name) VALUES ('ROLE_OPERADOR');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_RESPONSAVEL');

CREATE TABLE user_roles (
    user_id INTEGER REFERENCES users(id),
    role_id INTEGER REFERENCES roles(id)
);

CREATE TABLE user_responsaveis (
    user_id INTEGER REFERENCES users(id),
    responsavel_id INTEGER REFERENCES responsavel(id)
);







