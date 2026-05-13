package dam.mod.services.impl;

import java.util.List;

import dam.mod.models.Reserva;
import dam.mod.repositories.IReservaRepository;
import dam.mod.services.IReservaService;
import dam.mod.utils.Validaciones;

public class ReservaServiceImpl implements IReservaService {

    private final IReservaRepository repository;

    public ReservaServiceImpl(IReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reserva> findAll() {
        return repository.findAll();
    }

    @Override
    public Reserva findById(int id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Reserva reserva) {
        validar(reserva);
        return repository.save(reserva);
    }

    @Override
    public boolean update(Reserva reserva) {
        validar(reserva);
        return repository.update(reserva);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    private void validar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva no puede ser null");
        }

        Validaciones.validarEstadoReserva(reserva.getEstado());
    }
}