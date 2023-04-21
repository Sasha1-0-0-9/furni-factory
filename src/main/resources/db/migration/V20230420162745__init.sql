CREATE TABLE users
(
    id            bigint  PRIMARY KEY,
    first_name        VARCHAR(255) NOT NULL,
    surname       VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    date_of_birth DATE         NOT NULL
);

CREATE TABLE manufacturer
(
    id      bigint  PRIMARY KEY,
    company_name  VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    street  VARCHAR(255) NOT NULL
);

CREATE TABLE material
(
    id                     bigint  PRIMARY KEY,
    material_name                 VARCHAR(255) NOT NULL,
    description            VARCHAR(255) NOT NULL,
    price_for_square_meter bigint       NOT NULL,
    quantity               INTEGER      NOT NULL,
    manufacturer_id        bigint       NOT NULL
);

CREATE TABLE furniture
(
    id            bigint  PRIMARY KEY,
    furniture_type VARCHAR(255)     NOT NULL,
    price         double precision NOT NULL,
    height        double precision NOT NULL,
    width         double precision NOT NULL,
    "length"      double precision NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;

