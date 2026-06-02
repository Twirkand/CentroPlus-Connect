# 📘 Diccionario de Datos - CentroPlus Connect

---

## Tabla: usuarios

| Campo         | Tipo    | Nulo | Clave | Restricciones |
|--------------|--------|------|------|--------------|
| id           | INTEGER | No   | PK   | AUTOINCREMENT |
| nombre       | TEXT    | No   |      |              |
| dni          | TEXT    | No   | UQ   | UNIQUE       |
| email        | TEXT    | No   |      |              |
| telefono     | TEXT    | Sí   |      |              |
| tipo_usuario | TEXT    | No   |      | CHECK(ALUMNO, SOCIO, AMBOS) |
| password     | TEXT    | No   |      |              |
| activo       | INTEGER | No   |      | DEFAULT 1    |
| created_at   | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at   | TEXT    | Sí   |      |              |

---

## Tabla: usuarios_deleted

| Campo        | Tipo    | Nulo | Clave | Descripción |
|-------------|--------|------|------|-------------|
| id          | INTEGER | No   | PK   | Identificador interno |
| original_id | INTEGER | No   |      | ID original eliminado |
| nombre      | TEXT    | Sí   |      | Datos históricos |
| dni         | TEXT    | Sí   |      |              |
| email       | TEXT    | Sí   |      |              |
| telefono    | TEXT    | Sí   |      |              |
| tipo_usuario| TEXT    | Sí   |      |              |
| password    | TEXT    | Sí   |      |              |
| deleted_at  | TEXT    | No   |      | Fecha de eliminación |

---

## Tabla: actividades

| Campo            | Tipo    | Nulo | Clave | Restricciones |
|-----------------|--------|------|------|--------------|
| id              | INTEGER | No   | PK   | AUTOINCREMENT |
| nombre          | TEXT    | No   |      |              |
| tipo_actividad  | TEXT    | No   |      | CHECK(DEPORTIVA, ACADEMICA) |
| duracion        | INTEGER | No   |      |              |
| precio          | REAL    | No   |      | CHECK(precio >= 0) |
| plazas_maximas  | INTEGER | No   |      |              |
| plazas_ocupadas | INTEGER | No   |      | DEFAULT 0    |
| activo          | INTEGER | No   |      | DEFAULT 1    |
| created_at      | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at      | TEXT    | Sí   |      |              |

---

## Tabla: actividades_deleted

| Campo            | Tipo    | Nulo |
|-----------------|--------|------|
| id              | INTEGER | No   |
| original_id     | INTEGER | No   |
| nombre          | TEXT    | Sí   |
| tipo_actividad  | TEXT    | Sí   |
| duracion        | INTEGER | Sí   |
| precio          | REAL    | Sí   |
| plazas_maximas  | INTEGER | Sí   |
| plazas_ocupadas | INTEGER | Sí   |
| deleted_at      | TEXT    | No   |

---

## Tabla: reservas

| Campo        | Tipo    | Nulo | Clave | Relaciones |
|-------------|--------|------|------|-----------|
| id          | INTEGER | No   | PK   |           |
| id_usuario  | INTEGER | No   | FK   | usuarios(id) |
| id_actividad| INTEGER | No   | FK   | actividades(id) |
| fecha       | TEXT    | No   |      |           |
| estado      | TEXT    | No   |      | CHECK(ACTIVA, CANCELADA, COMPLETADA) |
| created_at  | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at  | TEXT    | Sí   |      |           |

---

## Tabla: reservas_deleted

| Campo        | Tipo    | Nulo |
|-------------|--------|------|
| id          | INTEGER | No   |
| original_id | INTEGER | No   |
| id_usuario  | INTEGER | Sí   |
| id_actividad| INTEGER | Sí   |
| fecha       | TEXT    | Sí   |
| estado      | TEXT    | Sí   |
| deleted_at  | TEXT    | No   |

---

## Tabla: incidencias

| Campo        | Tipo    | Nulo | Clave | Restricciones |
|-------------|--------|------|------|--------------|
| id          | INTEGER | No   | PK   | AUTOINCREMENT |
| id_usuario  | INTEGER | No   | FK   | usuarios(id) |
| asunto      | TEXT    | No   |      |              |
| descripcion | TEXT    | No   |      |              |
| fecha       | TEXT    | No   |      |              |
| estado      | TEXT    | No   |      | CHECK(ABIERTA, EN_PROCESO,CERRADA) |
| created_at  | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at  | TEXT    | Sí   |      |              |

---

## Tabla: incidencias_deleted

| Campo        | Tipo    | Nulo |
|-------------|--------|------|
| id          | INTEGER | No   |
| original_id | INTEGER | No   |
| id_usuario  | INTEGER | Sí   |
| asunto      | TEXT    | Sí   |
| descripcion | TEXT    | Sí   |
| fecha       | TEXT    | Sí   |
| estado      | TEXT    | Sí   |
| deleted_at  | TEXT    | No   |

---

## Tabla: remember_tokens

| Campo        | Tipo    | Nulo | Clave |
|-------------|--------|------|------|
| id          | INTEGER | No   | PK   |
| user_id     | INTEGER | No   | FK   |
| token_hash  | TEXT    | No   |      |
| expires_at  | TEXT    | No   |      |
| created_at  | TEXT    | No   | DEFAULT CURRENT_TIMESTAMP |

---

## Tabla: audit_log

| Campo        | Tipo    | Nulo |
|-------------|--------|------|
| id          | INTEGER | No   |
| table_name  | TEXT    | No   |
| record_id   | INTEGER | No   |
| action      | TEXT    | No   |
| old_data    | TEXT    | Sí   |
| new_data    | TEXT    | Sí   |
| created_at  | TEXT    | No   |

---

# Resumen del sistema

- Gestión de usuarios con roles
- Actividades deportivas y académicas
- Sistema de reservas
- Incidencias con estados
- Login persistente (remember me con tokens)
- Auditoría de cambios
- Soft delete completo con tablas históricas