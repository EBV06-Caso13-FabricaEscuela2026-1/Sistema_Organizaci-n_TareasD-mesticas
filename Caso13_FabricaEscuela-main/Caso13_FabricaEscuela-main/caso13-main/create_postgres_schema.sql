DROP TABLE IF EXISTS tareas CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;
DROP TABLE IF EXISTS grupos CASCADE;
DROP TABLE IF EXISTS estados CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles (
    id_rol integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_rol varchar(50) NOT NULL UNIQUE,
    CONSTRAINT chk_nombre_rol CHECK (nombre_rol IN ('ADMIN', 'USER'))
);

CREATE TABLE estados (
    id_estado integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_estado varchar(50) NOT NULL UNIQUE,
    fecha_actualizacion date NOT NULL,
    CONSTRAINT chk_nombre_estado CHECK (nombre_estado IN ('PENDIENTE', 'EN_PROCESO', 'TERMINADA'))
);

CREATE TABLE grupos (
    id_grupo integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_grupo varchar(255) NOT NULL,
    fecha_creacion date NOT NULL,
    codigo_acceso varchar(255) NOT NULL
);

CREATE TABLE usuarios (
    id_usuario integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    apellido varchar(255) NOT NULL,
    correo varchar(255) NOT NULL,
    usuario varchar(255) NOT NULL,
    contrasena varchar(255) NOT NULL,
    fecha_registro date NOT NULL,
    id_rol integer NOT NULL,
    id_grupo integer,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
    CONSTRAINT fk_usuario_grupo FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo)
);

CREATE TABLE tareas (
    id_tarea integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo varchar(255) NOT NULL,
    descripcion text,
    fecha_limite date NOT NULL,
    id_estado integer NOT NULL,
    id_grupo integer NOT NULL,
    id_usuario integer,
    nombre_miembro_asignado varchar(255),
    CONSTRAINT fk_tarea_estado FOREIGN KEY (id_estado) REFERENCES estados(id_estado),
    CONSTRAINT fk_tarea_grupo FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo),
    CONSTRAINT fk_tarea_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

INSERT INTO roles (nombre_rol) VALUES
('ADMIN'),
('USER');

INSERT INTO estados (nombre_estado, fecha_actualizacion) VALUES
('PENDIENTE', CURRENT_DATE),
('EN_PROCESO', CURRENT_DATE),
('TERMINADA', CURRENT_DATE);

CREATE TABLE detalle_tareas (
    id_detalle integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_tarea integer NOT NULL,
    descripcion text,
    observacion text,
    fecha_actualizacion date NOT NULL,

    CONSTRAINT fk_detalle_tarea
    FOREIGN KEY (id_tarea) REFERENCES tareas (id_tarea)
);