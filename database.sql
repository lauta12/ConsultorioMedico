
CREATE DATABASE IF NOT EXISTS consultorio_db;
USE consultorio_db;

DROP TABLE IF EXISTS doctores;
CREATE TABLE doctores (
    id_doctor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(50),
    email VARCHAR(100)
);

DROP TABLE IF EXISTS pacientes;
CREATE TABLE pacientes (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    obra_social VARCHAR(100),
    telefono VARCHAR(50)
);

DROP TABLE IF EXISTS turnos;
CREATE TABLE turnos (
    id_turno INT PRIMARY KEY AUTO_INCREMENT,
    dni_paciente VARCHAR(20) NOT NULL,
    id_doctor INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado ENUM('Pendiente', 'Completado', 'Cancelado') NOT NULL DEFAULT 'Pendiente',

    CONSTRAINT fk_turno_paciente
        FOREIGN KEY (dni_paciente) REFERENCES pacientes(dni),

    CONSTRAINT fk_turno_doctor
        FOREIGN KEY (id_doctor) REFERENCES doctores(id_doctor)
);