package dam.mod.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ActividadTest {
    Actividad actividad;
    int id = 1;
    String nombre = "Nombre";
    String tipo = "Tipo";
    int duracion = 1;
    int precio = 2;
    int plazas = 5;
    int ocupadas = 2;

    @BeforeEach
    void setup(){
        actividad = new Actividad(id, nombre, tipo, duracion, precio, plazas, ocupadas);
    }

    @DisplayName("Test verifica no null")
    @Order(1)
    @Test
    void actividadNoNullTest(){
        Assertions.assertNotNull(actividad, "La clase actividad no puede ser nula");
    }

    @DisplayName("Test verifica True")
    @Order(2)
    @Test
    void actividadEqualsTrueTest(){
        Actividad actividadNueva = new Actividad(1);
        Assertions.assertEquals(actividad, actividadNueva, "Debe de ser igual");
    }

    @DisplayName("Test verifica True")
    @Order(3)
    @Test
    void actividadEqualsFalseTest(){
        Actividad actividadNueva = new Actividad(2);
        Assertions.assertNotEquals(actividad, actividadNueva, "Debe de ser igual");
    }

    @DisplayName("Test verifica True")
    @Order(4)
    @Test
    void actividadEqualsTest(){
        Assertions.assertEquals(actividad, actividad, "Debe de ser igual");
    }

    // @DisplayName("Test verifica True")
    // @Order(5)
    // @Test
    // void actividadplazasDisponiblesOcupadas(){
    //     actividad.cancelarPlaza();
    //     Assertions.assertNotEquals(plazasOcupadas+1, actividad.getPlazasDid, "Debe de ser igual");
    // }
    

}
