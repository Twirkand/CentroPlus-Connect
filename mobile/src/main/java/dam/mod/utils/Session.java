package dam.mod.utils;

import dam.mod.models.Usuario;
import java.util.prefs.Preferences;

public class Session {

    /**
     * Guarda el usuario
     */
    private static Usuario currentUser;

    /**
     * Guarda datos en el sistema
     */
    private static final Preferences prefs =
            Preferences.userRoot().node("CentroPlusConnect");


    /**
     * Devuelve el usuario actual
     * 
     * @return el usuario
     */
    public static Usuario getCurrentUser() {
        return currentUser;
    }

    /**
     * Guarda la sesión
     * 
     * @param usuario el usuario
     */
    public static void setCurrentUser(Usuario usuario) {
        currentUser = usuario;

        if (usuario != null) {
            prefs.putInt("usuario_id", usuario.getId());
        }
    }

    /**
     * rRcuperar ID del usuario guardado
     * 
     * @return el id del usuario
     */
    public static int getSavedUserId() {
        return prefs.getInt("usuario_id", -1);
    }


    /**
     * Cierra la sesión
     */
    public static void logout() {
        currentUser = null;
        prefs.remove("usuario_id");
    }
}