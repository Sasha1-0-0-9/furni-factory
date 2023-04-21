CREATE TABLE orders
(
    id               BIGINT,
    user_id          BIGINT           NOT NULL,
    furniture_id     BIGINT           NOT NULL,
    status           VARCHAR(255)     NOT NULL,
    quantity         INT              NOT NULL,
    price            DOUBLE PRECISION NOT NULL,
    delivery_address VARCHAR(255)     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (furniture_id) REFERENCES furniture (id)
);