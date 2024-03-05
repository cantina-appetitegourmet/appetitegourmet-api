CREATE TABLE pagamento (
  id SERIAL PRIMARY KEY,
  contrato_id INTEGER REFERENCES contrato(id),
  id_meio_pagamento INTEGER  NOT NULL,
  id_empresa_integracao INTEGER  NOT NULL,
  id_status INTEGER  NOT NULL,
  valor DECIMAL(12, 2) NOT NULL,
  dados  VARCHAR(3000) NOT NULL
);