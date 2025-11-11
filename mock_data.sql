
USE consultorio_db;


DELETE FROM turnos WHERE id_turno > 0;
DELETE FROM pacientes WHERE dni IS NOT NULL;
DELETE FROM doctores WHERE id_doctor > 0;

INSERT INTO doctores (nombre, especialidad, telefono, email) VALUES
('Dra. Elena Gómez', 'Cardiología', '1145678901', 'elena.gomez@consultorio.com'),
('Dr. Ricardo Pérez', 'Pediatría', '1134567890', 'ricardo.perez@consultorio.com'),
('Dra. Laura Fernández', 'Dermatología', '1123456789', 'laura.fernandez@consultorio.com'),
('Dr. Carlos Sánchez', 'Traumatología', '1112345678', 'carlos.sanchez@consultorio.com'),
('Dra. Ana Martínez', 'Ginecología', '1156789012', 'ana.martinez@consultorio.com'),
('Dr. Jorge Díaz', 'Oftalmología', '1167890123', 'jorge.diaz@consultorio.com'),
('Dra. Sofía Torres', 'Neurología', '1178901234', 'sofia.torres@consultorio.com'),
('Dr. Martín Romero', 'Endocrinología', '1189012345', 'martin.romero@consultorio.com'),
('Dra. Valeria Castro', 'Clínica Médica', '1190123456', 'valeria.castro@consultorio.com'),
('Dr. Diego Alonso', 'Psiquiatría', '1101234567', 'diego.alonso@consultorio.com');

INSERT INTO pacientes (dni, nombre, apellido, obra_social, telefono) VALUES
('30123456', 'Juan', 'González', 'OSDE', '1511112222'),
('31234567', 'María', 'Rodríguez', 'Swiss Medical', '1522223333'),
('32345678', 'Lucas', 'López', 'Galeno', '1533334444'),
('33456789', 'Valentina', 'Martínez', 'OSDE', '1544445555'),
('34567890', 'Mateo', 'García', 'Medicus', '1555556666'),
('29876543', 'Camila', 'Fernández', 'Swiss Medical', '1566667777'),
('30987654', 'Benjamín', 'Díaz', 'Particular', '1577778888'),
('31098765', 'Isabella', 'Romero', 'Galeno', '1588889999'),
('32109876', 'Thiago', 'Sosa', 'OSDE', '1599990000'),
('33210987', 'Emma', 'Álvarez', 'Medicus', '1500001111'),
('34321098', 'Bautista', 'Pérez', 'Particular', '1512123434'),
('29432109', 'Martina', 'Sánchez', 'Swiss Medical', '1523234545'),
('30543210', 'Santino', 'Gómez', 'OSDE', '1534345656'),
('31654321', 'Olivia', 'Torres', 'Galeno', '1545456767'),
('32765432', 'Joaquín', 'Ruiz', 'Medicus', '1556567878'),
('33876543', 'Catalina', 'Vázquez', 'OSDE', '1567678989'),
('34987654', 'Felipe', 'Castro', 'Particular', '1578789090'),
('29098765', 'Mía', 'Suárez', 'Swiss Medical', '1589890101'),
('30109876', 'Valentín', 'Navarro', 'Galeno', '1590901212'),
('31210987', 'Alma', 'Ramírez', 'OSDE', '1501012323');

INSERT INTO turnos (dni_paciente, id_doctor, fecha, hora, estado) VALUES

('30123456', 1, '2025-11-20', '10:00:00', 'Pendiente'),
('31234567', 2, '2025-11-20', '10:30:00', 'Pendiente'),
('32345678', 3, '2025-11-21', '11:00:00', 'Pendiente'),
('33456789', 4, '2025-11-21', '11:30:00', 'Pendiente'),
('34567890', 5, '2025-11-22', '09:00:00', 'Pendiente'),
('29876543', 6, '2025-11-22', '09:30:00', 'Pendiente'),
('30987654', 7, '2025-11-24', '14:00:00', 'Pendiente'),
('31098765', 8, '2025-11-24', '14:30:00', 'Pendiente'),
('32109876', 9, '2025-11-25', '15:00:00', 'Pendiente'),
('33210987', 10, '2025-11-25', '15:30:00', 'Pendiente'),

('34321098', 1, '2025-11-03', '10:00:00', 'Completado'),
('29432109', 2, '2025-11-03', '10:30:00', 'Completado'),
('30543210', 3, '2025-11-04', '11:00:00', 'Completado'),
('31654321', 4, '2025-11-04', '11:30:00', 'Completado'),
('32765432', 5, '2025-11-05', '09:00:00', 'Completado'),
('33876543', 6, '2025-11-05', '09:30:00', 'Completado'),
('34987654', 7, '2025-11-06', '14:00:00', 'Completado'),
('29098765', 8, '2025-11-06', '14:30:00', 'Completado'),
('30109876', 9, '2025-11-07', '15:00:00', 'Completado'),
('31210987', 10, '2025-11-07', '15:30:00', 'Completado'),

('30123456', 3, '2025-11-10', '09:00:00', 'Cancelado'),
('31234567', 4, '2025-11-10', '09:30:00', 'Cancelado'),
('32345678', 5, '2025-11-10', '10:00:00', 'Cancelado'),
('33456789', 6, '2025-11-10', '10:30:00', 'Cancelado'),
('34567890', 7, '2025-11-11', '11:00:00', 'Cancelado'),
('29876543', 8, '2025-11-11', '11:30:00', 'Cancelado'),
('30987654', 9, '2025-11-11', '14:00:00', 'Cancelado'),
('31098765', 10, '2025-11-12', '14:30:00', 'Cancelado'),
('32109876', 1, '2025-11-12', '15:00:00', 'Cancelado'),
('33210987', 2, '2025-11-12', '15:30:00', 'Cancelado');