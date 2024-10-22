BEGIN TRANSACTION;

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE IF NOT EXISTS product (
    id VARCHAR(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    code VARCHAR(15) NOT NULL,
    name VARCHAR(63) NOT NULL,
    weight INTEGER CHECK (weight >= 0) NOT NULL,
    length NUMERIC(5, 2),
    width NUMERIC(5, 2),
    height NUMERIC(5, 2),
    images VARCHAR(255)[] NOT NULL,
    description VARCHAR(255),
    unit_preference VARCHAR(10) CHECK (unit_preference IN ('KG', 'PCS', 'BOXES')),
    variant_group_id VARCHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS category (
    id VARCHAR(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(63) NOT NULL,
    unit_preference VARCHAR(10) CHECK (unit_preference IN ('KG', 'PCS', 'BOXES')) NOT NULL,
    description VARCHAR(255),
    images VARCHAR(255)[] NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product_category (
    product_id VARCHAR(36) NOT NULL,
    category_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS customer (
    id VARCHAR(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(63) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(63),
    gstin VARCHAR(15),
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_request (
    id VARCHAR(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    customer_id VARCHAR(36) NOT NULL,
    date DATE NOT NULL,
    pdf VARCHAR(255),
    fulfilled BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_product (
    order_id VARCHAR(36) NOT NULL,
    product_id VARCHAR(36) NOT NULL,
    amount INTEGER CHECK (amount >= 0) NOT NULL,
    unit VARCHAR(10) CHECK (unit IN ('KG', 'PCS', 'BOXES')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES order_request (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_product_name ON product USING GIN (name gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_product_code ON product (code);

CREATE INDEX IF NOT EXISTS idx_product_variant_group_id ON product (variant_group_id);

CREATE INDEX IF NOT EXISTS idx_product_weight ON product (name, weight);

CREATE INDEX IF NOT EXISTS idx_product_height ON product (name, height);

CREATE INDEX IF NOT EXISTS idx_category_name ON category USING GIN (name gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_customer_name ON customer USING GIN (name gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_order_request_date ON order_request (date);

CREATE INDEX IF NOT EXISTS idx_order_request_customer_id ON order_request (customer_id);

CREATE TRIGGER update_product_timestamp
BEFORE UPDATE ON product
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_category_timestamp
BEFORE UPDATE ON category
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_product_category_timestamp
BEFORE UPDATE ON product_category
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_customer_timestamp
BEFORE UPDATE ON customer
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_order_request_timestamp
BEFORE UPDATE ON order_request
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_order_product_timestamp
BEFORE UPDATE ON order_product
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();


COMMIT;