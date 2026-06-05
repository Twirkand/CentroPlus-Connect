package dam.mod.repositories.sqlite;
 
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
import static org.junit.jupiter.api.Assertions.*;
 
@DisplayName("Tests de ConnectionManager")
class ConnectionManagerTest {
 
    @Test
    @Order(1)
    @DisplayName("getConnection: devuelve una conexión no nula")
    void getConnection_devuelveConexionNoNula() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
 
        assertNotNull(connection,
                "getConnection no debe devolver null");
 
        connection.close();
    }
 
    @Test
    @Order(2)
    @DisplayName("getConnection: devuelve una conexión abierta (no cerrada)")
    void getConnection_devuelveConexionAbierta() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
 
        assertFalse(connection.isClosed(),
                "La conexión devuelta debe estar abierta");
 
        connection.close();
    }
 
    @Test
    @Order(3)
    @DisplayName("getConnection: cada llamada devuelve una instancia distinta")
    void getConnection_devuelveNuevaInstanciaEnCadaLlamada() throws SQLException {
        Connection c1 = ConnectionManager.getConnection();
        Connection c2 = ConnectionManager.getConnection();
 
        assertNotSame(c1, c2,
                "Cada llamada debe devolver una conexión nueva e independiente");
 
        c1.close();
        c2.close();
    }
 
    @Test
    @Order(4)
    @DisplayName("getConnection: la conexión soporta ejecución de SQL básico")
    void getConnection_conexionEsFuncional() throws SQLException {
        try (Connection connection = ConnectionManager.getConnection()) {
            var rs = connection.createStatement().executeQuery("SELECT 1");
 
            assertTrue(rs.next(),
                    "La conexión debe poder ejecutar consultas SQL");
            assertEquals(1, rs.getInt(1));
        }
    }
 
    @Test
    @Order(5)
    @DisplayName("driver SQLite: el driver org.sqlite.JDBC está disponible en el classpath")
    void driver_sqLiteDisponibleEnClasspath() {
        assertDoesNotThrow(
                () -> Class.forName("org.sqlite.JDBC"),
                "El driver SQLite debe estar en el classpath");
    }
 
    @Test
    @Order(6)
    @DisplayName("driver SQLite: está registrado en DriverManager tras cargar la clase")
    void driver_registradoEnDriverManager() {
        var drivers = DriverManager.getDrivers();
 
        boolean sqLiteRegistrado = false;
        while (drivers.hasMoreElements()) {
            if (drivers.nextElement().getClass().getName().contains("sqlite")) {
                sqLiteRegistrado = true;
                break;
            }
        }
 
        assertTrue(sqLiteRegistrado,
                "El driver SQLite debe estar registrado en DriverManager");
    }
}