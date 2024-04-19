DROP TABLE IF EXISTS entry_logs CASCADE;

CREATE TABLE IF NOT EXISTS entry_logs (
    id SERIAL PRIMARY KEY,
    users_ip VARCHAR(15),
    users_id INT,
    entry_time TIMESTAMPTZ
);