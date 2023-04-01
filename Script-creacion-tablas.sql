---- Primero creamos la base de datos sistema-fidelizacion-clientes desde el dbeaver ----
---- Luego abrimos un script sql y ejecutamos lo que está debajo ----

CREATE TABLE cliente(
	id serial8 PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    num_documento VARCHAR(20) NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

CREATE TABLE puntos (
    id SERIAL8 PRIMARY KEY,
    descripcion_concepto VARCHAR(100) NOT NULL,
    puntos_requeridos INTEGER NOT NULL
);

CREATE TABLE reglas_asignacion_puntos (
    id SERIAL8 PRIMARY KEY,
    limite_inferior INTEGER NOT NULL,
    limite_superior INTEGER NOT NULL,
    monto_equivalencia_punto FLOAT NOT NULL
);

CREATE TABLE vencimiento_puntos (
    id SERIAL8 PRIMARY KEY,
    fecha_inicio_validez TIMESTAMP NOT NULL,
    fecha_fin_validez TIMESTAMP NOT NULL,
    dias_duracion_puntaje INTEGER NOT NULL
);

CREATE TABLE bolsa_puntos (
    id SERIAL8 PRIMARY KEY,
    id_cliente SERIAL8 NOT NULL,
    fecha_asignacion_puntaje TIMESTAMP NOT NULL,
    fecha_caducidad_puntaje TIMESTAMP NOT NULL,
    puntaje_asignado INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    saldo_puntos INTEGER NOT NULL,
    monto_operacion FLOAT NOT null,
	CONSTRAINT fk_cliente_bolsa_puntos FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);

CREATE TABLE cabecera (
    id SERIAL8 PRIMARY KEY,
    id_cliente SERIAL8 NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    fecha TIMESTAMP NOT NULL,
    concepto_uso_punto VARCHAR(100) NOT null,
    CONSTRAINT fk_cliente_cabecera FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);

CREATE TABLE detalle (
    id SERIAL8 PRIMARY KEY,
    id_cabecera SERIAL8 NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    id_bolsa_puntos_utilizada SERIAL8 NOT null,
    CONSTRAINT fk_cabecera_detalle FOREIGN KEY (id_cabecera) REFERENCES cabecera (id),
    CONSTRAINT fk_bolsa_puntos_detalle FOREIGN KEY (id_bolsa_puntos_utilizada) REFERENCES bolsa_puntos (id)
);