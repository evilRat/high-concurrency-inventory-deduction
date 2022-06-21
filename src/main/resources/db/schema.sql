DROP TABLE IF EXISTS specification ;
DROP TABLE IF EXISTS advertisement;
DROP TABLE IF EXISTS stockpile;
DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id          INTEGER PRIMARY KEY auto_increment,
    title       VARCHAR(50),
    price       DECIMAL,
    rate        FLOAT,
    description VARCHAR(8000),
    cover       VARCHAR(100),
    detail      VARCHAR(100)
);
CREATE INDEX product_title ON product (title);

CREATE TABLE stockpile
(
    id         INTEGER PRIMARY KEY,
    amount     INTEGER,
    frozen     INTEGER,
    product_id INTEGER
);
ALTER TABLE stockpile
    ADD CONSTRAINT fk_stockpile_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE;

CREATE TABLE specification
(
    id         INTEGER PRIMARY KEY,
    item       VARCHAR(50),
    v      VARCHAR(100),
    product_id INTEGER
);
ALTER TABLE specification
    ADD CONSTRAINT fk_specification_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE;

CREATE TABLE advertisement
(
    id         INTEGER PRIMARY KEY,
    image      VARCHAR(100),
    product_id INTEGER
);
ALTER TABLE advertisement
    ADD CONSTRAINT fk_advertisement_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE;
