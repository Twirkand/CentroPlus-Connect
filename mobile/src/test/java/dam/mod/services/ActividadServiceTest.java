package dam.mod.services;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dam.mod.models.Actividad;
import dam.mod.repositories.IActividadRepository;
import dam.mod.services.impl.ActividadServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ActividadServiceTest {

    IActividadService actividadservice;

    @Mock
    IActividadRepository actividadRepositoryMock;

    @BeforeEach
    void setup (){
        actividadservice = new ActividadServiceImpl(actividadRepositoryMock);
    }

    @Test
    void findById0Test(){
        Actividad actividad = actividadservice.findById(0);
        Assertions.assertNull(actividad);
    }

    @Test
    void findByIdTest(){
        Actividad actividad = new Actividad(1);

        when(actividadRepositoryMock.findById(anyInt())).thenReturn(actividad);
        Actividad actividadFind = actividadservice.findById(1);
        Assertions.assertNotNull(actividadFind);
    }
}
