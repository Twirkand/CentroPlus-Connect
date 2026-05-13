package services;

import models.Incidencia;
import java.util.List;

public class IncidenciaClient {

    public List<Incidencia> findAll() {
        return List.of();
    }

    public boolean create(Incidencia i) {
        return true;
    }

    public boolean cambiarEstado(int id, String estado) {
        return true;
    }
}