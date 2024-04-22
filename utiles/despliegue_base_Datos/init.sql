-- Crear un nuevo esquema llamado "planillas" y asignarle el usuario "postgres" como propietario
CREATE SCHEMA core AUTHORIZATION postgres;

-- Revocar todos los privilegios del esquema público
REVOKE ALL ON SCHEMA public FROM public;

-- Asignar todos los privilegios al nuevo esquema al usuario "postgres"
GRANT ALL ON SCHEMA core TO postgres;

CREATE TABLE core.ASEGURADOS (
    id NUMERIC(15) NOT NULL,
    id_tipo_de_identificacion NUMERIC(15) NOT NULL,
    numero_de_identificacion VARCHAR(20) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    nombres VARCHAR(50) NOT NULL,
    id_sexo NUMERIC(15) NOT NULL,
    fecha_de_nacimiento DATE,
    PRIMARY KEY (id)
);

CREATE SEQUENCE asegurados_id_seq START 1;

-- Tabla AMPAROS
CREATE TABLE core.AMPAROS (
    id NUMERIC(15) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE amparos_id_seq START 1;

-- Tabla PRIMAS
CREATE TABLE core.PRIMAS (
    id NUMERIC(15) NOT NULL,
    codigo_amparo NUMERIC(15) NOT NULL,
    edad_minima NUMERIC NOT NULL,
    edad_maxima NUMERIC NOT NULL,
    porcentaje_prima NUMERIC(15,5) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE primas_id_seq START 1;

-- Tabla TIPOS_IDENTIFICACION
CREATE TABLE core.TIPOS_IDENTIFICACION (
    id NUMERIC(15) NOT NULL,
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE tipos_identificacion_id_seq START 1;

-- Tabla SEXO
CREATE TABLE core.SEXO (
    id NUMERIC(15) NOT NULL,
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE sexo_id_seq START 1;

CREATE TABLE core.SEGUROS (
    id NUMERIC(15) NOT NULL,
    id_asegurado NUMERIC(15) NOT NULL,
    id_amparo NUMERIC(15) NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE seguros_id_seq START 1;

ALTER TABLE core.ASEGURADOS
ADD CONSTRAINT fk_tipo_identificacion
FOREIGN KEY (id_tipo_de_identificacion)
REFERENCES core.TIPOS_IDENTIFICACION(id);

ALTER TABLE core.ASEGURADOS
ADD CONSTRAINT fk_sexo
FOREIGN KEY (id_sexo)
REFERENCES core.SEXO(id);

ALTER TABLE core.SEGUROS
ADD CONSTRAINT fk_asegurado
FOREIGN KEY (id_asegurado)
REFERENCES core.ASEGURADOS (id);

ALTER TABLE core.SEGUROS
ADD CONSTRAINT fk_amparo
FOREIGN KEY (id_amparo)
REFERENCES core.AMPAROS (id);

ALTER TABLE core.PRIMAS
ADD CONSTRAINT fk_amparo
FOREIGN KEY (codigo_amparo)
REFERENCES core.AMPAROS (id);

INSERT INTO core.SEXO (id,descripcion) VALUES (NEXTVAL('sexo_id_seq'),'Masculino');
INSERT INTO core.SEXO (id,descripcion) VALUES (NEXTVAL('sexo_id_seq'),'Femenino');

INSERT INTO core.TIPOS_IDENTIFICACION (id,descripcion) VALUES (NEXTVAL('tipos_identificacion_id_seq'), 'CC');
INSERT INTO core.TIPOS_IDENTIFICACION (id,descripcion) VALUES (NEXTVAL('tipos_identificacion_id_seq'), 'CE');

INSERT INTO core.ASEGURADOS (id, id_tipo_de_identificacion, numero_de_identificacion, apellidos, nombres, id_sexo, fecha_de_nacimiento)
VALUES (NEXTVAL('asegurados_id_seq'), (select id from core.tipos_identificacion t where t.descripcion = 'CC') , '79000001', 'APELLIDO1', 'NOMBRE1', (select id from core.sexo s where s.descripcion = 'Masculino'), '1945-01-10');

INSERT INTO core.ASEGURADOS (id, id_tipo_de_identificacion, numero_de_identificacion, apellidos, nombres, id_sexo, fecha_de_nacimiento)
VALUES (NEXTVAL('asegurados_id_seq'), (select id from core.tipos_identificacion t where t.descripcion = 'CC'), '79000002', 'APELLIDO2', 'NOMBRE2', (select id from core.sexo s where s.descripcion = 'Masculino'), '1950-01-10');

INSERT INTO core.ASEGURADOS (id, id_tipo_de_identificacion, numero_de_identificacion, apellidos, nombres, id_sexo, fecha_de_nacimiento)
VALUES (NEXTVAL('asegurados_id_seq'), (select id from core.tipos_identificacion t where t.descripcion = 'CC'), '79000003', 'APELLIDO3', 'NOMBRE3', (select id from core.sexo s where s.descripcion = 'Femenino'), '1955-01-10');

INSERT INTO core.ASEGURADOS (id, id_tipo_de_identificacion, numero_de_identificacion, apellidos, nombres, id_sexo, fecha_de_nacimiento)
VALUES (NEXTVAL('asegurados_id_seq'), (select id from core.tipos_identificacion t where t.descripcion = 'CE'), '51000001', 'APELLIDO4', 'NOMBRE4', (select id from core.sexo s where s.descripcion = 'Femenino'), '1960-01-10');

INSERT INTO core.ASEGURADOS (id, id_tipo_de_identificacion, numero_de_identificacion, apellidos, nombres, id_sexo, fecha_de_nacimiento)
VALUES (NEXTVAL('asegurados_id_seq'), (select id from core.tipos_identificacion t where t.descripcion = 'CE'), '51000002', 'APELLIDO5', 'NOMBRE5', (select id from core.sexo s where s.descripcion = 'Femenino'), '1965-01-10');

INSERT INTO core.ASEGURADOS (id, id_tipo_de_identificacion, numero_de_identificacion, apellidos, nombres, id_sexo, fecha_de_nacimiento)
VALUES (NEXTVAL('asegurados_id_seq'), (select id from core.tipos_identificacion t where t.descripcion = 'CE'), '51000003', 'APELLIDO6', 'NOMBRE6', (select id from core.sexo s where s.descripcion = 'Femenino'), '1970-01-10');

INSERT INTO core.AMPAROS (id,nombre)
VALUES (NEXTVAL('amparos_id_seq'),'Muerte accidental');

INSERT INTO core.AMPAROS (id,nombre)
VALUES (NEXTVAL('amparos_id_seq'),'Desmembración');

INSERT INTO core.AMPAROS (id,nombre)
VALUES (NEXTVAL('amparos_id_seq'),'Auxilio funerario');

INSERT INTO core.AMPAROS (id,nombre)
VALUES (NEXTVAL('amparos_id_seq'),'Renta vitalicia');


INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 1, 18, 45, 0.02304);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 1, 46, 75, 0.02012);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 2, 18, 50, 0.1809);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 2, 51, 70, 0.16043);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 3, 18, 60, 0.14123);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 3, 61, 70, 0.1545);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 4, 18, 50, 0.12123);

INSERT INTO core.PRIMAS (id, codigo_amparo, edad_minima, edad_maxima, porcentaje_prima)
VALUES (NEXTVAL('primas_id_seq'), 4, 51, 70, 0.1345);

INSERT INTO core.SEGUROS (id,id_asegurado, id_amparo)
VALUES
    (NEXTVAL('seguros_id_seq'), 6, 1),
    (NEXTVAL('seguros_id_seq'), 3, 2),
    (NEXTVAL('seguros_id_seq'), 2, 3),
    (NEXTVAL('seguros_id_seq'), 5, 4),
    (NEXTVAL('seguros_id_seq'), 1, 1),
    (NEXTVAL('seguros_id_seq'), 3, 2),
    (NEXTVAL('seguros_id_seq'), 4, 3),
    (NEXTVAL('seguros_id_seq'), 5, 4),
    (NEXTVAL('seguros_id_seq'), 2, 1);
