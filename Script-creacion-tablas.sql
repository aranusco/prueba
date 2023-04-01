---- Primero creamos la base de datos sistema-fidelizacion-clientes desde el dbeaver ----
---- Luego abrimos un script sql y ejecutamos lo que está debajo ----

CREATE TABLE cliente(
	id_cliente integer NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    num_documento VARCHAR(20) NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
);
CREATE SEQUENCE public.cliente_sec;

CREATE TABLE puntos (
    id_puntos integer NOT NULL,
    descripcion_concepto VARCHAR(100) NOT NULL,
    puntos_requeridos INTEGER NOT NULL,
    CONSTRAINT puntos_pkey PRIMARY KEY (id_puntos)
);
CREATE SEQUENCE public.puntos_sec;

CREATE TABLE reglas_asignacion_puntos (
    id_reglas integer NOT NULL,
    limite_inferior INTEGER NOT NULL,
    limite_superior INTEGER NOT NULL,
    monto_equivalencia_punto FLOAT NOT NULL,
    CONSTRAINT reglas_pkey PRIMARY KEY (id_reglas)
);
CREATE SEQUENCE public.reglas_sec;

CREATE TABLE vencimiento_puntos (
    id_vencimiento integer NOT NULL,
    fecha_inicio_validez TIMESTAMP NOT NULL,
    fecha_fin_validez TIMESTAMP NOT NULL,
    dias_duracion_puntaje INTEGER NOT NULL,
    CONSTRAINT vencimiento_pkey PRIMARY KEY (id_vencimiento)
);
CREATE SEQUENCE public.vencimiento_sec;

CREATE TABLE bolsa_puntos (
    id_bolsa integer NOT NULL,
    id_cliente integer NOT NULL,
    fecha_asignacion_puntaje TIMESTAMP NOT NULL,
    fecha_caducidad_puntaje TIMESTAMP NOT NULL,
    puntaje_asignado INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    saldo_puntos INTEGER NOT NULL,
    monto_operacion FLOAT NOT null,
    CONSTRAINT bolsa_pkey PRIMARY KEY (id_bolsa),
	CONSTRAINT fk_cliente_bolsa_puntos FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);
CREATE SEQUENCE public.bolsa_sec;
CREATE TABLE cabecera (
    id_cabecera integer NOT NULL,
    id_cliente integer NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    fecha TIMESTAMP NOT NULL,
    concepto_uso_punto VARCHAR(100) NOT null,
    CONSTRAINT cabecera_pkey PRIMARY KEY (id_cabecera),
    CONSTRAINT fk_cliente_cabecera FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);
CREATE SEQUENCE public.cabecera_sec;
CREATE TABLE detalle (
    id_detalle integer NOT NULL,
    id_cabecera integer NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    id_bolsa_puntos_utilizada SERIAL8 NOT null,
    CONSTRAINT detalle_pkey PRIMARY KEY (id_detalle),
    CONSTRAINT fk_cabecera_detalle FOREIGN KEY (id_cabecera) REFERENCES cabecera (id_cabecera),
    CONSTRAINT fk_bolsa_puntos_detalle FOREIGN KEY (id_bolsa_puntos_utilizada) REFERENCES bolsa_puntos (id_bolsa)
);
CREATE SEQUENCE public.detalle_sec;