PRAGMA foreign_keys = ON;

BEGIN TRANSACTION;


-- LIMPIEZA TOTAL (ORDEN CORRECTO)


DELETE FROM reservas;
DELETE FROM incidencias;
DELETE FROM remember_tokens;

DELETE FROM actividades;
DELETE FROM usuarios;

DELETE FROM audit_log;

-- TABLAS DE BORRADOS
DELETE FROM usuarios_deleted;
DELETE FROM actividades_deleted;
DELETE FROM reservas_deleted;
DELETE FROM incidencias_deleted;

-- SQLITE SEQUENCES (reset autoincrement)
DELETE FROM sqlite_sequence;


-- USUARIOS

INSERT INTO usuarios (nombre, dni, email, telefono, tipo_usuario, password, activo)
VALUES
('Ana Pérez','11111111A','ana@email.com','600111111','ALUMNO','123456789',1),
('Luis Ramos','22222222B','luis@email.com','600222222','SOCIO','123456789',1),
('Marta Díaz','33333333C','marta@email.com','600333333','AMBOS','123456789',1),
('Admin Sistema','99999999Z','admin@email.com','600999999','ADMIN','admin123',1);


-- ACTIVIDADES

INSERT INTO actividades (nombre, tipo_actividad, duracion, precio, plazas_maximas, plazas_ocupadas, activo)
VALUES
('Yoga','DEPORTIVA',60,25.5,15,8,1),
('Programación Java','ACADEMICA',90,40.0,20,12,1),
('Spinning','DEPORTIVA',45,18.0,12,12,1),
('Inglés técnico','ACADEMICA',60,30.0,18,6,1),
('Sistemas Linux','ACADEMICA',120,45.0,16,10,1);


-- RESERVAS (SIN HARD-CODE DE IDs)

INSERT INTO reservas (id_usuario, id_actividad, fecha, estado)
VALUES
(
 (SELECT id FROM usuarios WHERE dni='11111111A'),
 (SELECT id FROM actividades WHERE nombre='Yoga'),
 '2025-01-10',
 'ACTIVA'
),
(
 (SELECT id FROM usuarios WHERE dni='22222222B'),
 (SELECT id FROM actividades WHERE nombre='Programación Java'),
 '2025-01-11',
 'ACTIVA'
),
(
 (SELECT id FROM usuarios WHERE dni='33333333C'),
 (SELECT id FROM actividades WHERE nombre='Spinning'),
 '2025-01-12',
 'CANCELADA'
);


-- INCIDENCIAS

INSERT INTO incidencias (id_usuario, asunto, descripcion, fecha, estado)
VALUES
(
 (SELECT id FROM usuarios WHERE dni='11111111A'),
 'Problema con reserva',
 'No puedo reservar una plaza',
 '2025-01-12',
 'ABIERTA'
),
(
 (SELECT id FROM usuarios WHERE dni='22222222B'),
 'Cambio de horario',
 'El horario no coincide',
 '2025-01-13',
 'EN_PROCESO'
),
(
 (SELECT id FROM usuarios WHERE dni='33333333C'),
 'Error en app',
 'La aplicación se cierra',
 '2025-01-14',
 'CERRADA'
);


-- TOKENS

INSERT INTO remember_tokens (user_id, token_hash, expires_at)
VALUES
(
 (SELECT id FROM usuarios WHERE dni='11111111A'),
 'hash_token_ana',
 '2026-06-30 00:00:00'
);

COMMIT;