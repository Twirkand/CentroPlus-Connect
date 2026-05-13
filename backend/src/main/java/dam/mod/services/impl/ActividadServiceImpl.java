package dam.mod.services.impl;

import java.util.List;

import dam.mod.models.Actividad;
import dam.mod.repositories.IActividadRepository;
import dam.mod.services.IActividadService;
import dam.mod.utils.Validaciones;

public class ActividadServiceImpl implements IActividadService {

    private final IActividadRepository repository;

    public ActividadServiceImpl(IActividadRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Actividad> findAll() {
        return repository.findAll();
    }

    @Override
    public Actividad findById(int id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Actividad actividad) {
        validar(actividad);
        return repository.save(actividad);
    }

    @Override
    public boolean update(Actividad actividad) {
        validar(actividad);
        return repository.update(actividad);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    private void validar(Actividad actividad) {
        if (actividad == null) {
            throw new IllegalArgumentException("Actividad no puede ser null");
        }

        Validaciones.validarTipoActividad(actividad.getTipoActividad());
        Validaciones.validarNoVacio(actividad.getNombre(), "Nombre actividad");
    }
}