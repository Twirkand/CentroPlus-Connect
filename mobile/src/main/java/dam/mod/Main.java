package dam.mod;

import dam.mod.models.Usuario;
import dam.mod.repositories.impl.UsuarioRepository;
import dam.mod.services.IUsuarioService;
import dam.mod.services.impl.UsuarioServiceImpl;
import dam.mod.utils.ScreenManager;
import dam.mod.utils.Session;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación.
 * 
 * @author Alberto Carballo Hernández
 * @author Nauzet Torres Tejera
 * @version 1.0 - SNAPSHOT
 * 
 */
public class Main extends Application {

    /**
     * Método principal de inicio de la aplicación JavaFX.
     *
     * @param stage ventana principal de la aplicación
     */
    @Override
    public void start(Stage stage) {

        /**
         * Inicializa el gestor de pantallas con el Stage principal
         */
        ScreenManager.init(stage);

        /**
         * Servicio de usuarios con acceso a repositorio
         */
        IUsuarioService service = new UsuarioServiceImpl(new UsuarioRepository());

        /**
         * Recupera el ID de usuario guardado en sesión
         */
        int id = Session.getSavedUserId();

        /**
         * Si hay sesión guardada, recupera el usuario desde la BD
         */
        if (id != -1) {
            Usuario usuario = service.findById(id);
            Session.setCurrentUser(usuario);
        }

        /**
         * Configura el icono de la aplicación
         */
        stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/icons/app.png")));

        /**
         * Configuración básica de la ventana
         */
        stage.setTitle("CentroPlus-Connect");
        stage.setResizable(false);

        /**
         * Decide qué pantalla mostrar si existe sesión
         */
        if (Session.getCurrentUser() != null) {
            ScreenManager.change("inicio.fxml");
        } else {
            ScreenManager.change("login.fxml");
        }

        /**
         * Muestra la ventana principal
         */
        stage.show();
    }

    /**
     * Punto de entrada estándar de Java.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }
}