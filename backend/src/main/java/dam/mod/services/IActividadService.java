package dam.mod.services;

import java.util.List;

import dam.mod.models.Actividad;

public interface IActividadService {
    List<Actividad> findAll();
    Actividad findById(int id);
    boolean create(Actividad actividad);
    boolean update(Actividad actividad);
    boolean delete(int id);
    boolean reservarPlaza(int idActividad);
    boolean cancelarPlaza(int idActividad);
    List<Actividad> findCompletas();
    int calcularPlazasDisponibles(int idActividad);
    double calcularIngresosTotales();
}