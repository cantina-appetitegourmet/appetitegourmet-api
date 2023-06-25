CREATE TABLE desconto (
  id SERIAL PRIMARY KEY,
  motivo VARCHAR(255) NOT NULL,
  valor_percentual DECIMAL(5, 2) NOT NULL CHECK (valor_percentual >= 0 AND valor_percentual <= 100)
);
INSERT INTO desconto (motivo_id, valor_percentual) VALUES
  ('2 ou mais filhos', 5),
  ('Desconto concedido em negociação por serem 3 filhos', 7),
  ('Desconto especial por ter contratado pacote completo', 10),
  ('funcionário da escola', 5),
  ('Desconto especial para familiar', 50),
  ('Desconto concedido em negociação', 7);


 
CREATE TABLE contrato (
  id SERIAL PRIMARY KEY,
  responsavel_aluno_id INTEGER REFERENCES responsavel_aluno(id),
  turma_anos_letivos_id INTEGER REFERENCES turma_anos_letivos(id),
  data_adesao DATE DEFAULT CURRENT_DATE
);

-- Criação da trigger para preencher turma_anos_letivos_id
CREATE OR REPLACE FUNCTION set_turma_anos_letivos_id()
  RETURNS TRIGGER AS
$$
BEGIN
  NEW.turma_anos_letivos_id := (
    SELECT turma_anos_letivos_id
    FROM responsavel_aluno
    WHERE id = NEW.aluno_responsavel_id
  );
  RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Criação da trigger que chama a função set_turma_anos_letivos_id
CREATE TRIGGER fill_turma_anos_letivos_id
BEFORE INSERT ON contrato
FOR EACH ROW
EXECUTE FUNCTION set_turma_anos_letivos_id();

CREATE TABLE contrato_desconto (
  id SERIAL PRIMARY KEY,
  contrato_id INTEGER REFERENCES contrato(id),
  desconto_id INTEGER REFERENCES desconto(id),
  data_inicio DATE DEFAULT CURRENT_DATE,
  data_fim DATE
);


CREATE TABLE contrato_plano (
  id SERIAL PRIMARY KEY,
  contrato_id INTEGER REFERENCES contrato(id),
  planos_alimentares_precos_id INTEGER REFERENCES planos_alimentares_precos(id),
  segunda BOOLEAN,
  terca BOOLEAN,
  quarta BOOLEAN,
  quinta BOOLEAN,
  sexta BOOLEAN,
  sabado BOOLEAN,
  domingo BOOLEAN,
  preco_dia DECIMAL(10, 2)
  data_inicio DATE DEFAULT CURRENT_DATE,
  data_fim DATE
);

CREATE OR REPLACE FUNCTION calcular_preco_dia()
  RETURNS TRIGGER AS $$
BEGIN
  SELECT preco_dia INTO NEW.preco_dia
  FROM planos_alimentares_precos
  WHERE id = NEW.planos_alimentares_precos_id;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_calcular_preco_dia
BEFORE INSERT ON contrato_plano
FOR EACH ROW
EXECUTE FUNCTION calcular_preco_dia();
