package dam.mod.controllers;

import dam.mod.models.Actividad;
import dam.mod.models.Usuario;
import dam.mod.repositories.IActividadRepository;
import dam.mod.repositories.IReservaRepository;
import dam.mod.repositories.IUsuarioRepository;
import dam.mod.repositories.impl.ActividadRepository;
import dam.mod.repositories.impl.ReservaRepository;
import dam.mod.repositories.impl.UsuarioRepository;
import dam.mod.services.IActividadService;
import dam.mod.services.IReservaService;
import dam.mod.services.IUsuarioService;
import dam.mod.services.impl.ActividadServiceImpl;
import dam.mod.services.impl.ReservaServiceImpl;
import dam.mod.services.impl.UsuarioServiceImpl;
import dam.mod.utils.ScreenManager;
import dam.mod.utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador del detalle de una actividad.
 *
 * Permite visualizar la información de una actividad y realizar reservas.
 * Gestiona la interacción entre el usuario y el sistema de reservas.
 */
public class DetalleActividadController {

    /**
     * Nombre de la actividad.
     */
    @FXML
    private Label lblNombre;

    /**
     * Tipo de actividad.
     */
    @FXML
    private Label lblTipo;

    /**
     * Duración de la actividad.
     */
    @FXML
    private Label lblDuracion;

    /**
     * Precio de la actividad.
     */
    @FXML
    private Label lblPrecio;

    /**
     * Plazas disponibles de la actividad.
     */
    @FXML
    private Label lblPlazas;

    private static Actividad actividadSeleccionada;

    private IActividadService actividadService;
    private IReservaService reservaService;

    /**
     * Establece la actividad seleccionada.
     *
     * @param a actividad seleccionada
     */
    public static void setActividad(Actividad a) {
        actividadSeleccionada = a;
    }

    /**
     * Inicializa el controlador.
     *
     * Verifica sesión activa, inicializa servicios y carga los datos de la actividad.
     */
    @FXML
    public void initialize() {

        Usuario user = Session.getCurrentUser();
        if (user == null) {
            ScreenManager.change("login.fxml");
            return;
        }

        IActividadRepository actividadRepo = new ActividadRepository();
        actividadService = new ActividadServiceImpl(actividadRepo);

        IReservaRepository reservaRepo = new ReservaRepository();

        IUsuarioRepository usuarioRepo = new UsuarioRepository();
        IUsuarioService usuarioService = new UsuarioServiceImpl(usuarioRepo);

        reservaService = new ReservaServiceImpl(
                reservaRepo,
                usuarioService,
                actividadService
        );

        if (actividadSeleccionada != null) {
            cargarDatos();
        }
    }

    /**
     * Carga los datos de la actividad en la interfaz.
     */
    private void cargarDatos() {

        lblNombre.setText("Nombre: " + actividadSeleccionada.getNombre());
        lblTipo.setText("Tipo: " + actividadSeleccionada.getTipoActividad());
        lblDuracion.setText("Duración: " + actividadSeleccionada.getDuracion() + " min");
        lblPrecio.setText("Precio: " + actividadSeleccionada.getPrecio() + " €");

        int disponibles =
                actividadSeleccionada.getPlazasMaximas()
                        - actividadSeleccionada.getPlazasOcupadas();

        lblPlazas.setText("Plazas disponibles: " + disponibles);
    }

    /**
     * Realiza la reserva de la actividad para el usuario actual.
     *
     * Valida si ya está reservada o si hay plazas disponibles.
     */
    @FXML
    private void reservar() {

        int usuarioId = Session.getCurrentUser().getId();
        int actividadId = actividadSeleccionada.getId();

        if (reservaService.yaReservado(actividadId, usuarioId)) {
            System.out.println("Ya tienes esta actividad reservada");
            return;
        }

        int disponibles =
                actividadSeleccionada.getPlazasMaximas()
                        - actividadSeleccionada.getPlazasOcupadas();

        if (disponibles <= 0) {
            System.out.println("No hay plazas disponibles");
            return;
        }

        boolean ok = reservaService.reservar(actividadId, usuarioId);

        if (ok) {

            actividadSeleccionada =
                    actividadService.findById(actividadId);

            cargarDatos();

            System.out.println("Reserva realizada correctamente");

        } else {
            System.out.println("Error al realizar la reserva");
        }
    }

    /**
     * Vuelve a la pantalla de actividades.
     */
    @FXML
    private void volver() {
        ScreenManager.change("actividades.fxml");
    }
}