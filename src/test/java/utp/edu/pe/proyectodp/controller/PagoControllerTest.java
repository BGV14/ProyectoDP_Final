package utp.edu.pe.proyectodp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.service.PagoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagoService service;

    private Pago pago;
    private Pago guardado;

    @BeforeEach
    void setup() {
        pago = Pago.builder().monto(100.0).metodoPago("YAPE").build();
        guardado = Pago.builder().id(1L).monto(100.0).metodoPago("YAPE")
                .codigoPago("PAG-0001").estadoPago("PROCESADO").build();
    }

    @DisplayName("Controller - Registrar pago exitosamente")
    @Test
    void registrar_returnsCreated() throws Exception {
        when(service.guardar(any())).thenReturn(guardado);

        mockMvc.perform(post("/api/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pago)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estadoPago").value("PROCESADO"))
                .andExpect(jsonPath("$.codigoPago").value("PAG-0001"));
    }

    @DisplayName("Controller - Pago rechazado por regla de negocio devuelve 409")
    @Test
    void registrar_conReglaDeNegocioViolada_returnsConflict() throws Exception {
        when(service.guardar(any()))
                .thenThrow(new IllegalStateException("Debe iniciar sesión para realizar esta operación."));
        mockMvc.perform(post("/api/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pago)))
                .andExpect(status().isConflict());
    }

    @DisplayName("Controller - Error inesperado devuelve 500")
    @Test
    void registrar_conErrorInesperado_returnsInternalServerError() throws Exception {
        when(service.guardar(any())).thenThrow(new RuntimeException("fallo inesperado"));

        mockMvc.perform(post("/api/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pago)))
                .andExpect(status().isInternalServerError());
    }

    @DisplayName("Controller - Buscar por id existente")
    @Test
    void buscarPorId_existente_returnsOk() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(java.util.Optional.of(guardado));

        mockMvc.perform(get("/api/pagos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metodoPago").value("YAPE"));
    }

    @DisplayName("Controller - Buscar por id inexistente devuelve 404")
    @Test
    void buscarPorId_inexistente_returnsNotFound() throws Exception {
        when(service.buscarPorId(99L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/pagos/99"))
                .andExpect(status().isNotFound());
    }
}
