CREATE TABLE IF NOT EXISTS rol (
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(25) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (nombre)
);

INSERT IGNORE INTO rol (id,nombre) VALUES
(1,"ROL_ADMIN"),
(2,"ROL_GERENTE"),
(3,"ROL_AUXILIAR"),
(4,"ROL_CLIENTE");