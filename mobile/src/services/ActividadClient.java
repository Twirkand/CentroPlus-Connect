package services;

import models.Actividad;
import java.util.ArrayList;
import java.util.List;

public class ActividadClient {

    public List<Actividad> findAll() {
        List<Actividad> lista = new ArrayList<>();

        lista.add(new Actividad(1, "Natación", 10, 3));
        lista.add(new Actividad(2, "Yoga", 8, 8));

        return lista;
    }

    public boolean reservar(int idActividad) {
        return true;
    }

    public boolean cancelar(int idActividad) {
        return true;
    }
}