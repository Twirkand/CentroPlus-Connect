package dam.mod.controllers;

import dam.mod.utils.ScreenManager;
import dam.mod.utils.Session;
import javafx.fxml.FXML;

public class InicioController {

    //inicializacion
    @FXML
    public void initialize() {

        if (Session.getCurrentUser() == null) {
            ScreenManager.change("login.fxml");
        }
    }

    //abrir actividades
    @FXML
    private void abrirActividades() {
        ScreenManager.change("actividades.fxml");
    }

    //abrir reservas
    @FXML
    private void abrirReservas() {
        ScreenManager.change("reservas.fxml");
    }

    //abrir incidencias
    @FXML
    private void abrirIncidencias() {
        ScreenManager.change("incidencias.fxml");
    }

    //abrir perfil
    @FXML
    private void abrirPerfil() {
        ScreenManager.change("perfil.fxml");
    }

    // IDIOMA
    @FXML
    private void setSpanish() {
        aplicarIdioma("es");
    }

    @FXML
    private void setEnglish() {
        aplicarIdioma("en");
    }

    @FXML
    private void setGerman() {
        aplicarIdioma("de");
    }

    private void aplicarIdioma(String lang) {

        switch (lang) {
            case "es" -> System.out.println("Idioma: Español");
            case "en" -> System.out.println("Idioma: English");
            case "de" -> System.out.println("Idioma: Deutsch");
        }
    }
}