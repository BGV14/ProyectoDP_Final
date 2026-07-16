package utp.edu.pe.proyectodp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.service.AuthService;
import utp.edu.pe.proyectodp.service.PagoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PagoServiceAdapterTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private PagoService pagoService;

    @Test
    void guardarPagoConYapeUsaElAdapterYQuedaProcesado() {
        // El singleton exige sesión iniciada, así que autenticamos primero
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
                .metodoPago("PAYPAL") // no tiene adapter
                .build();

        Pago guardado = pagoService.guardar(pago);

        assertEquals("PENDIENTE", guardado.getEstadoPago());
    }

    @Test
    void guardarPagoSinSesionLanzaExcepcion() {
        // No hacemos login: SesionSistema.validarAutenticacion() debe rechazar
        // Nota: como el Singleton es estático, este test debe correr aislado
        // o resetear el estado si otro test ya autenticó antes.
        Pago pago = Pago.builder()
                .monto(50.0)
                .fechaPago("2026-07-15")
                .metodoPago("VISA")
                .build();

        assertThrows(IllegalStateException.class, () -> pagoService.guardar(pago));
    }
}