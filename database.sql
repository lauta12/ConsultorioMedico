-- ---
-- Script de Base de Datos (Estilo snake_case)
-- Base de datos: consultorio_db
-- ---
CREATE DATABASE IF NOT EXISTS consultorio_db;
USE consultorio_db;

-- ---
-- Tabla de Doctores
-- (id_doctor ya estaba bien)
-- ---
DROP TABLE IF EXISTS doctores;
CREATE TABLE doctores (
    id_doctor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(50),
    email VARCHAR(100)
);

-- ---
-- Tabla de Pacientes
-- (obraSocial -> obra_social)
-- ---
DROP TABLE IF EXISTS pacientes;
CREATE TABLE pacientes (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    obra_social VARCHAR(100), -- <-- CAMBIO AQUÍ
    telefono VARCHAR(50)
);

-- ---
-- Tabla de Turnos
-- (idTurno -> id_turno, dniPaciente -> dni_paciente, idDoctor -> id_doctor)
-- ---
DROP TABLE IF EXISTS turnos;
CREATE TABLE turnos (
    id_turno INT PRIMARY KEY AUTO_INCREMENT, -- <-- CAMBIO AQUÍ
    dni_paciente VARCHAR(20) NOT NULL,        -- <-- CAMBIO AQUÍ
    id_doctor INT NOT NULL,                   -- <-- CAMBIO AQUÍ
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado ENUM('pendiente', 'completado', 'cancelado') NOT NULL DEFAULT 'pendiente',

    -- Llaves Foráneas (actualizadas a snake_case)
    CONSTRAINT fk_turno_paciente
        FOREIGN KEY (dni_paciente) REFERENCES pacientes(dni), -- <-- CAMBIO AQUÍ

    CONSTRAINT fk_turno_doctor
        FOREIGN KEY (id_doctor) REFERENCES doctores(id_doctor) -- <-- CAMBIO AQUÍ
);