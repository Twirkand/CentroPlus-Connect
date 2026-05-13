package dam.mod.services;

import java.util.List;

import dam.mod.models.Reserva;

public interface IReservaService {

    List<Reserva> findAll();

    Reserva findById(int id);

    boolean create(Reserva reserva);

    boolean update(Reserva reserva);

    boolean delete(int id);

    boolean cancelarReserva(int idReserva);

    boolean existeReservaUsuarioActividad(int idUsuario, int idActividad);

    boolean comprobarPlazasDisponibles(int idActividad);
}
