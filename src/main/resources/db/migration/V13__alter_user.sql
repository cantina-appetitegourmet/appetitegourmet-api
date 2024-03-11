ALTER TABLE colegios DROP COLUMN endereco_id;
ALTER TABLE users ADD pessoa_id int4 NULL;
ALTER TABLE users ADD FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);