CREATE TABLE equipos(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30),
score INT(100)
);

CREATE TABLE partidos(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
equipo1 VARCHAR(30),
score1 INT(100),
equipo2 VARCHAR(30),
score2 INT(100),
status VARCHAR(30)
);