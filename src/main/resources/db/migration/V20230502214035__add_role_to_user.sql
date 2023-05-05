ALTER TABLE users
    ADD COLUMN IF NOT EXISTS role VARCHAR(255) NOT NULL DEFAULT 'USER';

CREATE TABLE IF NOT EXISTS "role" (
    id BIGINT PRIMARY KEY,
    "type" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS role_user
(
    user_id bigint REFERENCES users (id),
    role_id bigint REFERENCES "role" (id),
    PRIMARY KEY (user_id, role_id)
);