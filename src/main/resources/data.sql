DROP TABLE IF EXISTS member;

CREATE TABLE member (
    id INTEGER PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    location TEXT
);