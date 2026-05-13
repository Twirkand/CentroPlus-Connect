package dam.mod.services.impl;

import java.util.List;

import dam.mod.models.Incidencia;
import dam.mod.repositories.IIncidenciaRepository;
import dam.mod.services.IIncidenciaService;
import dam.mod.utils.Validaciones;

public class IncidenciaServiceImpl implements IIncidenciaService {

    private final IIncidenciaRepository repository;

    public IncidenciaServiceImpl(IIncidenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Incidencia> findAll() {
        return repository.findAll();
    }

    @Override
    public Incidencia findById(int id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Incidencia incidencia) {
        validar(incidencia);
        return repository.save(incidencia);
    }

    @Override
    public boolean update(Incidencia incidencia) {
        validar(incidencia);
        return repository.update(incidencia);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    private void validar(Incidencia incidencia) {
        if (incidencia == null) {
            throw new IllegalArgumentException("Incidencia no puede ser null");
        }

        Validaciones.validarEstadoIncidencia(incidencia.getEstado());
        Validaciones.validarNoVacio(incidencia.getDescripcion(), "Descripción");
    }
}