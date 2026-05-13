package controllers;

import javafx.scene.control.ListView;
import models.Actividad;
import services.ActividadClient;

import java.util.List;

import javax.swing.text.html.ListView;

public class MainController {

    @FXML
    private ListView<String> listView;

    private ActividadClient actividadClient = new ActividadClient();

    @FXML
    public void initialize() {
        cargarActividades();
    }

    private void cargarActividades() {
        List<Actividad> actividades = actividadClient.findAll();

        for (Actividad a : actividades) {
            listView.getItems().add(
                    a.getNombre() +
                    " | Plazas: " +
                    a.getPlazasOcupadas() + "/" +
                    a.getPlazasMaximas()
            );
        }
    }
}