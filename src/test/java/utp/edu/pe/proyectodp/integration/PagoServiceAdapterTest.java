package utp.edu.pe.proyectodp.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.service.AuthService;
import utp.edu.pe.proyectodp.service.PagoService;
import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PagoServiceAdapterTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private PagoService pagoService;

    @BeforeEach
    void setup() {
        ConfiguracionSistema.getInstancia().setMantenimiento(false);

        boolean login = authService.login("admin", "1234");

        if (!login) {
            throw new IllegalStateException("No se pudo iniciar sesión de prueba");
        }
    }

    @Test
    void guardarPagoConYapeUsaElAdapterYQuedaProcesado() {
        authService.login("admin", "1234");
        Pago pago = Pago.builder()
                .monto(150.0)
                .fechaPago("2026-07-15")
                .metodoPago("YAPE")
                .build();

        Pago guardado = pagoService.guardar(pago);

        assertNotNull(guardado.getId());
        assertNotNull(guardado.getCodigoPago(), "El código debe generarse vía GeneradorCodigo (Singleton)");
        assertEquals("PROCESADO", guardado.getEstadoPago(), "El Adapter debió procesar el pago");
    }

    @Test
    void guardarPagoConMetodoNoReconocidoQuedaPendiente() {
        authService.login("admin", "1234");

        Pago pago = Pago.builder()
                .monto(80.0)
                .fechaPago("2026-07-15")
                .metodoPago("PAYPAL")
                .build();

        Pago guardado = pagoService.guardar(pago);

        assertEquals("PENDIENTE", guardado.getEstadoPago());
    }

    @Test
    void guardarPagoSinSesionLanzaExcepcion() {

        SesionSistema.getInstancia().cerrarSesion();

        Pago pago = Pago.builder()
                .monto(50.0)
                .fechaPago("2026-07-15")
                .metodoPago("VISA")
                .build();

        assertThrows(
                IllegalStateException.class,
                () -> pagoService.guardar(pago)
        );
    }
}