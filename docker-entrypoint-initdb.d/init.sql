CREATE DATABASE rinha_backend;

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    limite BIGINT NOT NULL,
    saldo BIGINT NOT NULL
);

INSERT INTO cliente (id, limite, saldo)
VALUES
    (1, 100000, 0),
    (2, 80000, 0),
    (3, 1000000, 0),
    (4, 10000000, 0),
    (5, 500000, 0);