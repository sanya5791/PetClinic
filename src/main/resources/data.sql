DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL
);

INSERT INTO user (first_name, last_name) VALUES
    ('first_name_1', 'last_name_1'),
    ('first_name_2', 'last_name_2'),
    ('first_name_3', 'last_name_3');