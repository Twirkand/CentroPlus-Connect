package dam.mod.services;

import java.util.List;

import dam.mod.models.Incidencia;

public interface IIncidenciaService {
    List<Incidencia> findAll();
    Incidencia findById(int id);
    boolean create(Incidencia incidencia);
    boolean update(Incidencia incidencia);
    boolean delete(int id);
}