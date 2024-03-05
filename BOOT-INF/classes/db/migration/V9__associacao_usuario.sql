CREATE TABLE associacao_usuario (
    
	id SERIAL PRIMARY KEY,
	usuario_id INTEGER REFERENCES users(id),
    role_id INTEGER REFERENCES roles(id),
    associado_id INTEGER UNIQUE
);
