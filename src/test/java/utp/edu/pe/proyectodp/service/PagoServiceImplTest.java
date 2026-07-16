package utp.edu.pe.proyectodp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.repository.PagoRepository;
import utp.edu.pe.proyectodp.service.impl.PagoServiceImpl;
import utp.edu.pe.proyectodp.service.pattern.adapter.adapters.BCPAdapter;
import utp.edu.pe.proyectodp.service.pattern.adapter.adapters.MastercardAdapter;
import utp.edu.pe.proyectodp.service.pattern.adapter.adapters.YapeAdapter;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;
import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoServiceImplTest {

    @Mock
    private PagoRepository repoMock;

    private PagoServiceImpl service;

    @BeforeEach
    void setup() {
        ConfiguracionSistema.getInstancia().setMantenimiento(false);
        SesionSistema.getInstancia().iniciarSesion("admin", "ADMINISTRADOR");

        List<ProcesadorPago> procesadores = List.of(
                new YapeAdapter(),
                new BCPAdapter(),
                new MastercardAdapter()
        );

        service = new PagoServiceImpl(repoMock, procesadores);

        // Simula el @PostConstruct que Spring ejecuta automáticamente
        service.indexarProcesadores();
    }

    @DisplayName("Service - Pago con YAPE usa el Adapter y queda PROCESADO")
    @Test
    void guardar_conYape_quedaProcesado() {
        when(repoMock.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Pago pago = Pago.builder().monto(100.0).metodoPago("YAPE").build();
        Pago resultado = service.guardar(pago);
        assertThat(resultado.getEstadoPago()).isEqualTo("PROCESADO");
        assertThat(resultado.getCodigoPago()).isNotNull();
    }

    @DisplayName("Service - Pago con BCP usa el Adapter y queda PROCESADO")
    @Test
    void guardar_conBcp_quedaProcesado() {
        when(repoMock.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Pago pago = Pago.builder().monto(250.0).metodoPago("BCP").build();
        Pago resultado = service.guardar(pago);
        assertThat(resultado.getEstadoPago()).isEqualTo("PROCESADO");
    }

    @DisplayName("Service - Metodo de pago no reconocido queda PENDIENTE")
    @Test
    void guardar_conMetodoNoReconocido_quedaPendiente() {
        when(repoMock.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Pago pago = Pago.builder().monto(80.0).metodoPago("PAYPAL").build();
        Pago resultado = service.guardar(pago);
        assertThat(resultado.getEstadoPago()).isEqualTo("PENDIENTE");
    }

    @DisplayName("Service - Sistema en mantenimiento rechaza el pago")
    @Test
    void guardar_conMantenimientoActivo_lanzaExcepcion() {
        ConfiguracionSistema.getInstancia().setMantenimiento(true);
        Pago pago = Pago.builder().monto(50.0).metodoPago("VISA").build();
        assertThrows(IllegalStateException.class, () -> service.guardar(pago));
    }

    @DisplayName("Service - Sin sesion iniciada rechaza el pago")
    @Test
    void guardar_sinSesion_lanzaExcepcion() {
        SesionSistema.getInstancia().cerrarSesion();
        Pago pago = Pago.builder().monto(50.0).metodoPago("PLIN").build();
        assertThrows(IllegalStateException.class, () -> service.guardar(pago));
    }

    @DisplayName("Service - No sobrescribe un codigo de pago ya asignado")
    @Test
    void guardar_conCodigoExistente_noLoRegenera() {
        when(repoMock.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Pago pago = Pago.builder().monto(30.0).metodoPago("MASTERCARD")
                .codigoPago("PAG-9999").build();
        Pago resultado = service.guardar(pago);
        assertThat(resultado.getCodigoPago()).isEqualTo("PAG-9999");
    }
}