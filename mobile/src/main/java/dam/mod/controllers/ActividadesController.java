package dam.mod.controllers;

import dam.mod.models.Actividad;
import dam.mod.repositories.IActividadRepository;
import dam.mod.repositories.impl.ActividadRepository;
import dam.mod.services.IActividadService;
import dam.mod.services.impl.ActividadServiceImpl;
import dam.mod.utils.ScreenManager;
import dam.mod.utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Controlador de la pantalla de actividades.
 *
 * Permite listar las actividades disponibles, seleccionar una actividad
 * y navegar a su pantalla de detalle.
 */
public class ActividadesController {

    /**
     * Lista visual de actividades disponibles.
     */
    @FXML
    private ListView<Actividad> listaActividades;

    private IActividadService actividadService;

    /**
     * Inicializa el controlador.
     *
     * Verifica sesión activa, inicializa el servicio y carga las actividades.
     */
    @FXML
    public void initialize() {

        if (Session.getCurrentUser() == null) {
            ScreenManager.change("login.fxml");
            return;
        }

        IActividadRepository repo = new ActividadRepository();
        actividadService = new ActividadServiceImpl(repo);

        cargarActividades();
    }

    /**
     * Carga todas las actividades en la lista visual.
     */
    private void cargarActividades() {

        List<Actividad> actividades = actividadService.findAll();

        listaActividades.getItems().clear();
        listaActividades.getItems().addAll(actividades);
    }

    /**
     * Selecciona una actividad y abre su detalle.
     */
    @FXML
    private void seleccionarActividad() {

        Actividad seleccionada =
                listaActividades.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            DetalleActividadController.setActividad(seleccionada);
            ScreenManager.change("detalle_actividad.fxml");
        }
    }

    /**
     * Vuelve a la pantalla principal.
     */
    @FXML
    private void volver() {
        ScreenManager.change("inicio.fxml");
    }
}