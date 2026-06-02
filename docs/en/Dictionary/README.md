# 📘 Data Dictionary - CentroPlus Connect

---

## Table: usuarios

| Field         | Type    | Null | Key | Constraints |
|--------------|--------|------|------|--------------|
| id           | INTEGER | No   | PK   | AUTOINCREMENT |
| nombre       | TEXT    | No   |      |              |
| dni          | TEXT    | No   | UQ   | UNIQUE       |
| email        | TEXT    | No   |      |              |
| telefono     | TEXT    | Yes  |      |              |
| tipo_usuario | TEXT    | No   |      | CHECK(ALUMNO, SOCIO, AMBOS) |
| password     | TEXT    | No   |      |              |
| activo       | INTEGER | No   |      | DEFAULT 1    |
| created_at   | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at   | TEXT    | Yes  |      |              |

---

## Table: usuarios_deleted

| Field        | Type    | Null | Key | Description |
|-------------|--------|------|------|-------------|
| id          | INTEGER | No   | PK   | Internal identifier |
| original_id | INTEGER | No   |      | Original deleted ID |
| nombre      | TEXT    | Yes  |      | Historical data |
| dni         | TEXT    | Yes  |      |              |
| email       | TEXT    | Yes  |      |              |
| telefono    | TEXT    | Yes  |      |              |
| tipo_usuario| TEXT    | Yes  |      |              |
| password    | TEXT    | Yes  |      |              |
| deleted_at  | TEXT    | No   |      | Deletion date |

---

## Table: actividades

| Field            | Type    | Null | Key | Constraints |
|-----------------|--------|------|------|--------------|
| id              | INTEGER | No   | PK   | AUTOINCREMENT |
| nombre          | TEXT    | No   |      |              |
| tipo_actividad  | TEXT    | No   |      | CHECK(DEPORTIVA, ACADEMICA) |
| duracion        | INTEGER | No   |      |              |
| precio          | REAL    | No   |      | CHECK(price >= 0) |
| plazas_maximas  | INTEGER | No   |      |              |
| plazas_ocupadas | INTEGER | No   |      | DEFAULT 0    |
| activo          | INTEGER | No   |      | DEFAULT 1    |
| created_at      | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at      | TEXT    | Yes  |      |              |

---

## Table: actividades_deleted

| Field            | Type    | Null |
|-----------------|--------|------|
| id              | INTEGER | No   |
| original_id     | INTEGER | No   |
| nombre          | TEXT    | Yes  |
| tipo_actividad  | TEXT    | Yes  |
| duracion        | INTEGER | Yes  |
| precio          | REAL    | Yes  |
| plazas_maximas  | INTEGER | Yes  |
| plazas_ocupadas | INTEGER | Yes  |
| deleted_at      | TEXT    | No   |

---

## Table: reservas

| Field        | Type    | Null | Key | Relationships |
|-------------|--------|------|------|--------------|
| id          | INTEGER | No   | PK   |              |
| id_usuario  | INTEGER | No   | FK   | usuarios(id) |
| id_actividad| INTEGER | No   | FK   | actividades(id) |
| fecha       | TEXT    | No   |      |              |
| estado      | TEXT    | No   |      | CHECK(ACTIVA, CANCELADA, COMPLETED) |
| created_at  | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at  | TEXT    | Yes  |      |              |

---

## Table: reservas_deleted

| Field        | Type    | Null |
|-------------|--------|------|
| id          | INTEGER | No   |
| original_id | INTEGER | No   |
| id_usuario  | INTEGER | Yes  |
| id_actividad| INTEGER | Yes  |
| fecha       | TEXT    | Yes  |
| estado      | TEXT    | Yes  |
| deleted_at  | TEXT    | No   |

---

## Table: incidencias

| Field        | Type    | Null | Key | Constraints |
|-------------|--------|------|------|--------------|
| id          | INTEGER | No   | PK   | AUTOINCREMENT |
| id_usuario  | INTEGER | No   | FK   | usuarios(id) |
| asunto      | TEXT    | No   |      |              |
| descripcion | TEXT    | No   |      |              |
| fecha       | TEXT    | No   |      |              |
| estado      | TEXT    | No   |      | CHECK(OPEN, IN_PROGRESS, CLOSED) |
| created_at  | TEXT    | No   |      | DEFAULT CURRENT_TIMESTAMP |
| updated_at  | TEXT    | Yes  |      |              |

---

## Table: incidencias_deleted

| Field        | Type    | Null |
|-------------|--------|------|
| id          | INTEGER | No   |
| original_id | INTEGER | No   |
| id_usuario  | INTEGER | Yes  |
| asunto      | TEXT    | Yes  |
| descripcion | TEXT    | Yes  |
| fecha       | TEXT    | Yes  |
| estado      | TEXT    | Yes  |
| deleted_at  | TEXT    | No   |

---

## Table: remember_tokens

| Field        | Type    | Null | Key |
|-------------|--------|------|------|
| id          | INTEGER | No   | PK   |
| user_id     | INTEGER | No   | FK   |
| token_hash  | TEXT    | No   |      |
| expires_at  | TEXT    | No   |      |
| created_at  | TEXT    | No   | DEFAULT CURRENT_TIMESTAMP |

---

## Table: audit_log

| Field        | Type    | Null |
|-------------|--------|------|
| id          | INTEGER | No   |
| table_name  | TEXT    | No   |
| record_id   | INTEGER | No   |
| action      | TEXT    | No   |
| old_data    | TEXT    | Yes  |
| new_data    | TEXT    | Yes  |
| created_at  | TEXT    | No   |

---

# System Summary

- User management with roles
- Sports and academic activities
- Reservation system
- Incident management with states
- Persistent login (remember me with tokens)
- Audit logging system
- Full soft delete system with historical tables