ALTER TABLE users ALTER COLUMN id SET NOT NULL;
ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY (id);

CREATE TABLE IF NOT EXISTS products
(
    id BIGSERIAL,
    account_number BIGINT,
    balance NUMERIC,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);