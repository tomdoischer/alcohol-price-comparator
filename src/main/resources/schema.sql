-- CREATE TABLE FOR WHISKY_BOTTLES
CREATE TABLE IF NOT EXISTS whisky_bottle (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    average_price_all FLOAT8,
    average_price_past_30_days FLOAT8,
);

-- CREATE TABLE FOR WHISKY_BOTTLE_UPDATE
CREATE TABLE IF NOT EXISTS whisky_bottle_update (
    id SERIAL PRIMARY KEY,
    name TEXT,
    whisky_bottle_id INTEGER,
    price FLOAT8,
    link TEXT,
    in_stock BOOLEAN,
    timestamp TIMESTAMP,
    FOREIGN KEY (whisky_bottle_id) REFERENCES whisky_bottle(id)
);