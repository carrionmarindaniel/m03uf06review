DROP DATABASE IF EXISTS empleados;
CREATE DATABASE empleados;
USE empleados;
CREATE TABLE empleados(
	edad int NOT NULL,
	nombre varchar(32) NOT NULL PRIMARY KEY,
	sueldo double NOT NULL,
    contratado_actualmente boolean not null,
	fecha_inicio_contrato date NOT NULL,
    fecha_fin_contrato date NOT NULL,
	plus long NOT NULL,
	genero char not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO empleados VALUES(25,'Alberto Gutierrez',1852.25,true,'2020-5-26','2020-8-12',102.36,'M');
INSERT INTO empleados VALUES(28,'Lucia Lopez',1902.52,true,'2018-7-15','2021-1-27',24.46,'F')
