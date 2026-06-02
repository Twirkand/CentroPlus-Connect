package dam.mod.controllers;

import dam.mod.models.Usuario;
import dam.mod.repositories.impl.RememberTokenRepositoryImpl;
import dam.mod.services.IUsuarioService;
import dam.mod.services.impl.UsuarioServiceImpl;
import dam.mod.utils.ScreenManager;
import dam.mod.utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de la pantalla de perfil del usuario.
 *
 * Gestiona la visualización de los datos del usuario autenticado
 * y permite acciones como cerrar sesión o cambiar contraseña.
 */
public class PerfilController {

    private IUsuarioService service;

    public void setService(IUsuarioService service) {
        this.service = service;
    }

    /**
     * Nombre del usuario.
     */
    @FXML
    private Label lblNombre;

    /**
     * DNI del usuario.
     */
    @FXML
    private Label lblDni;

    /**
     * Email del usuario.
     */
    @FXML
    private Label lblEmail;

    /**
     * Teléfono del usuario.
     */
    @FXML
    private Label lblTelefono;

    /**
     * Tipo de usuario (ALUMNO, SOCIO, etc.).
     */
    @FXML
    private Label lblTipo;

    /**
     * Inicializa la vista del perfil.
     *
     * Obtiene el usuario en sesión y carga sus datos en pantalla.
     * Si no hay sesión activa, redirige al login.
     */
    @FXML
    public void initialize() {

        Usuario u = Session.getCurrentUser();

        if (u == null) {
            ScreenManager.change("login.fxml");
            return;
        }

        lblNombre.setText(u.getNombre());
        lblDni.setText(u.getDni());
        lblEmail.setText(u.getEmail());
        lblTelefono.setText(u.getTelefono());
        lblTipo.setText(u.getTipoUsuario());
    }

    /**
     * Vuelve a la pantalla principal.
     */
    @FXML
    private void volver() {
        ScreenManager.change("inicio.fxml");
    }

    /**
     * Cierra la sesión del usuario actual y redirige al login.
     */
    @FXML
    private void cerrarSesion() {
        service.logout();
        ScreenManager.change("login.fxml");
    }

    /**
     * Abre la pantalla para cambiar la contraseña del usuario.
     */
    @FXML
    private void abrirCambiarPassword() {
        ScreenManager.change("cambiar_password.fxml");
    }

    @FXML
    private void abrirCambiarEmail() {
        ScreenManager.change("cambiar_email.fxml");
    }

    @FXML
    private void abrirCambiarTelefono() {
        ScreenManager.change("cambiar_telefono.fxml");
    }

}