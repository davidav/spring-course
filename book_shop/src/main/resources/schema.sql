DROP TABLE IF EXISTS books;

CREATE TABLE books
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    author_id INT NOT NULL,
    title     VARCHAR(250) NOT NULL,
    price_old VARCHAR(250) DEFAULT NULL,
    price     VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(250) NOT NULL,
    surname VARCHAR(250) NOT NULL
);

ALTER TABLE books
    ADD CONSTRAINT fk_book_author
        FOREIGN KEY(author_id) REFERENCES authors(id);