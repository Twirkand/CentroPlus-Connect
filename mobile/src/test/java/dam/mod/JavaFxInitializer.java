package dam.mod;
 
import javafx.application.Platform;
 
/**
 * Inicializa el toolkit de JavaFX en modo headless una sola vez para todos los tests.
 *
 * Uso: llama a JavaFXInitializer.init() en un @BeforeAll de cada clase de test
 * que use mocks de componentes JavaFX (Label, TextField, ListView, etc.)
 */
public class JavaFxInitializer {
 
    private static boolean initialized = false;
 
    public static synchronized void init() {
        if (!initialized) {
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("glass.platform", "Monocle");
            System.setProperty("monocle.platform", "Headless");
 
            try {
                Platform.startup(() -> {});
            } catch (IllegalStateException e) {
                // Ya estaba inicializado, no pasa nada
            }
            initialized = true;
        }
    }
}