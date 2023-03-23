DROP TABLE IF EXISTS MOVIE;

CREATE TABLE MOVIE (
                        id INT AUTO_INCREMENT  PRIMARY KEY,
                        name VARCHAR(250) NOT NULL,
                        type VARCHAR(250) NOT NULL,
                        price INT NOT NULL
);