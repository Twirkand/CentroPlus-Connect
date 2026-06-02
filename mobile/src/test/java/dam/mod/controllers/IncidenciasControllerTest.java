package dam.mod.controllers;
 
import dam.mod.models.Incidencia;
import dam.mod.models.Usuario;
import dam.mod.services.IIncidenciaService;
import dam.mod.utils.ScreenManager;
import dam.mod.utils.Session;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
 
/**
 * Tests unitarios para IncidenciasController.
 *
 * Escenarios cubiertos:
 * - Sin sesión → redirige al login.
 * - enviarIncidencia() con campos vacíos → no llama al servicio.
 * - enviarIncidencia() con asunto vacío → no llama al servicio.
 * - enviarIncidencia() con datos válidos → crea y recarga.
 * - enviarIncidencia() con error en servicio → limpia campos sin recargar.
 * - seleccionarIncidencia() sin selección → no navega.
 * - seleccionarIncidencia() con selección → navega al detalle.
 * - volver() → navega a inicio.fxml.
 */
@ExtendWith(MockitoExtension.class)
class IncidenciasControllerTest {
 
    private IncidenciasController controller;
    private IIncidenciaService incidenciaService = mock(IIncidenciaService.class);
 
    @SuppressWarnings("unchecked")
    private ListView<Incidencia> listaIncidencias = mock(ListView.class);
    @SuppressWarnings("unchecked")
    private ObservableList<Incidencia> items = mock(ObservableList.class);
    private TextField txtAsunto = mock(TextField.class);
    private TextArea txtDescripcion = mock(TextArea.class);
 
    private final Usuario usuario = usuarioMock();
 
    @BeforeEach
    void setUp() throws Exception {
        controller = new IncidenciasController();
        setField("incidenciaService", incidenciaService);
        setField("usuarioActual", usuario);
        setField("listaIncidencias", listaIncidencias);
        setField("txtAsunto", txtAsunto);
        setField("txtDescripcion", txtDescripcion);
        when(listaIncidencias.getItems()).thenReturn(items);
    }
 
    @Test
    void initialize_sinSesion_redirigirALogin() {
        try (MockedStatic<Session> sessionMock = mockStatic(Session.class);
             MockedStatic<ScreenManager> screenMock = mockStatic(ScreenManager.class)) {
 
            sessionMock.when(Session::getCurrentUser).thenReturn(null);
            invoke("initialize");
            screenMock.verify(() -> ScreenManager.change("login.fxml"));
        }
    }
 
    @Test
    void enviarIncidencia_camposVacios_noLlamaServicio() {
        when(txtAsunto.getText()).thenReturn("");
        when(txtDescripcion.getText()).thenReturn("");
 
        invoke("enviarIncidencia");
 
        verify(incidenciaService, never()).create(any());
    }
 
    @Test
    void enviarIncidencia_asuntoVacio_noLlamaServicio() {
        when(txtAsunto.getText()).thenReturn("   ");
        when(txtDescripcion.getText()).thenReturn("Descripción válida");
 
        invoke("enviarIncidencia");
 
        verify(incidenciaService, never()).create(any());
    }
 
    @Test
    void enviarIncidencia_datosValidos_creaYRecarga() {
        when(txtAsunto.getText()).thenReturn("Problema con la sala");
        when(txtDescripcion.getText()).thenReturn("La puerta no abre");
        when(incidenciaService.create(any())).thenReturn(true);
        when(incidenciaService.findByUsuario(usuario.getId())).thenReturn(List.of());
 
        invoke("enviarIncidencia");
 
        verify(incidenciaService).create(any(Incidencia.class));
        verify(items, atLeastOnce()).setAll(any(java.util.Collection.class));
        verify(txtAsunto).clear();
        verify(txtDescripcion).clear();
    }
 
    @Test
    void enviarIncidencia_errorServicio_limpiaCamposSinRecargar() {
        when(txtAsunto.getText()).thenReturn("Asunto");
        when(txtDescripcion.getText()).thenReturn("Descripcion");
        when(incidenciaService.create(any())).thenReturn(false);
 
        invoke("enviarIncidencia");
 
        // El controlador limpia los campos siempre, independientemente del resultado
        verify(txtAsunto).clear();
        verify(txtDescripcion).clear();
        // Pero NO recarga la lista si el servicio falla
        verify(incidenciaService, never()).findByUsuario(anyInt());
    }
 
    @Test
    @SuppressWarnings("unchecked")
    void seleccionarIncidencia_sinSeleccion_noNavega() {
        try (MockedStatic<ScreenManager> screenMock = mockStatic(ScreenManager.class)) {
            var model = mock(javafx.scene.control.MultipleSelectionModel.class);
            when(listaIncidencias.getSelectionModel()).thenReturn(model);
            when(model.getSelectedItem()).thenReturn(null);
 
            invokeConEvento("seleccionarIncidencia");
 
            screenMock.verifyNoInteractions();
        }
    }
 
    @Test
    @SuppressWarnings("unchecked")
    void seleccionarIncidencia_conSeleccion_navegaADetalle() {
        try (MockedStatic<ScreenManager> screenMock = mockStatic(ScreenManager.class)) {
            Incidencia inc = new Incidencia();
            inc.setId(7);
            var model = mock(javafx.scene.control.MultipleSelectionModel.class);
            when(listaIncidencias.getSelectionModel()).thenReturn(model);
            when(model.getSelectedItem()).thenReturn(inc);
 
            invokeConEvento("seleccionarIncidencia");
 
            screenMock.verify(() -> ScreenManager.setIncidenciaId(7));
            screenMock.verify(() -> ScreenManager.change("detalle_incidencia.fxml"));
        }
    }
 
    @Test
    void volver_navegaAInicio() {
        try (MockedStatic<ScreenManager> screenMock = mockStatic(ScreenManager.class)) {
            invoke("volver");
            screenMock.verify(() -> ScreenManager.change("inicio.fxml"));
        }
    }
 
    private Usuario usuarioMock() {
        Usuario u = new Usuario();
        u.setId(5);
        return u;
    }
 
    private void setField(String name, Object value) throws Exception {
        Field f = IncidenciasController.class.getDeclaredField(name);
        f.setAccessible(true);
        f.set(controller, value);
    }
 
    private void invoke(String methodName) {
        try {
            Method m = IncidenciasController.class.getDeclaredMethod(methodName);
            m.setAccessible(true);
            m.invoke(controller);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    private void invokeConEvento(String methodName) {
        try {
            Method m = IncidenciasController.class.getDeclaredMethod(
                    methodName, javafx.scene.input.MouseEvent.class);
            m.setAccessible(true);
            m.invoke(controller, (Object) null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}