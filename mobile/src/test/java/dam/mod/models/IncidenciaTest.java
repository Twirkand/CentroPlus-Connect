package dam.mod.models;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IncidenciaTest {

    Incidencia incidencia;
    int id = 1;
    int idUsuario = 2;
    String asunto = "asunto";
    String descripcion = "descripcion";
    LocalDate fecha = LocalDate.now();
    String estado = "activo";

    @BeforeEach
    void setup(){
        incidencia = new Incidencia(id,idUsuario,asunto, descripcion, fecha,estado);
    }

    @Test
    void incidenciaNotNull(){
        Assertions.assertNotNull(incidencia);
    }

    @Test
    void incidenciaEqualsTrueTest(){
        Incidencia incidenciaNueva = new Incidencia(1);
        Assertions.assertEquals(incidencia, incidenciaNueva);
    }
    
}
