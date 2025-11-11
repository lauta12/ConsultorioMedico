-- ---
-- Asumimos que estás usando una base de datos llamada 'consultorio_db'
-- Si no, crea una: CREATE DATABASE consultorio_db;
-- Y luego:          USE consultorio_db;
-- ---


-- Tabla de Doctores
-- Creada a partir de tu clase Doctor.java
CREATE TABLE doctores (
    id_doctor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(50),
    email VARCHAR(100)
);

-- Tabla de Pacientes
-- Creada a partir de tu clase Paciente.java
-- Usamos DNI como PRIMARY KEY porque es único y tu clase Turno lo usa para la relación.
CREATE TABLE pacientes (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    obraSocial VARCHAR(100),
    telefono VARCHAR(50)
);

-- Tabla de Turnos
-- Creada a partir de tu clase Turno.java
-- Aquí se conectan las tres tablas.
CREATE TABLE turnos (
    idTurno INT PRIMARY KEY AUTO_INCREMENT,
    dniPaciente VARCHAR(20) NOT NULL,
    idDoctor INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(50) DEFAULT 'Pendiente',

    -- --- Llaves Foráneas (Las "Mejores Prácticas") ---
    -- Esto conecta el turno con un paciente existente
    CONSTRAINT fk_turno_paciente
        FOREIGN KEY (dniPaciente) REFERENCES pacientes(dni),

    -- Esto conecta el turno con un doctor existente
    CONSTRAINT fk_turno_doctor
        FOREIGN KEY (idDoctor) REFERENCES doctores(id_doctor)
);