BEGIN TRANSACTION;

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE IF NOT EXISTS product (
    id VARCHAR(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    code VARCHAR(15) NOT NULL,
    name VARCHAR(63) NOT NULL,
    weight INTEGER NOT NULL,
    length NUMERIC(5, 2) NOT NULL,
    width NUMERIC(5, 2) NOT NULL,
    height NUMERIC(5, 2) NOT NULL,
    images TEXT[] NOT NULL,
    description TEXT NOT NULL,
    unit_preference ENUM('KG', 'PCS', 'BOXES') NOT NULL
);

CREATE TABLE IF NOT EXISTS category (
    id VARCHAR(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(63) NOT NULL,
    unit_preference ENUM('KG', 'PCS', 'BOXES') NOT NULL,
    description TEXT NOT NULL,
    images TEXT[] NOT NULL
);

CREATE TABLE IF NOT EXISTS product_category (
    product_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);


CREATE INDEX idx_product_name ON product USING GIN (name gin_trgm_ops);

CREATE INDEX idx_product_code ON product (code);

CREATE INDEX idx_product_name_weight ON product (name, weight);

CREATE INDEX idx_product_name_height ON product (name, height);

-- CREATE INDEX idx_category_name ON category USING GIN (name gin_trgm_ops);

COMMIT;