CREATE TABLE IF NOT EXISTS rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    index INT UNIQUE NOT NULL,
    operation VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO rule (id, index, operation, description) VALUES
    (1, 1, 'REVERSE', 'Reverse the given string.'),
    (2, 2, 'MD5', 'Convert the string to checksum.'),
    (3, 3, 'UPPERCASE', 'Convert the string to uppercase.');