package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.PagoRepository;
import utp.edu.pe.proyectodp.service.PagoService;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.*;
import utp.edu.pe.proyectodp.service.pattern.adapter.adapters.*;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;
import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;
import utp.edu.pe.proyectodp.service.pattern.singlenton.GeneradorCodigo;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;

    @Override
    public List<Pago> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Pago> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Pago guardar(Pago pago) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        if (pago.getCodigoPago() == null || pago.getCodigoPago().isBlank()) {
            pago.setCodigoPago(GeneradorCodigo.getInstancia().generarCodigoPago());
        }

        ProcesadorPago procesador = resolverAdaptador(pago.getMetodoPago());
        if (procesador != null) {
            procesador.procesarPago(pago.getMonto());
            pago.setEstadoPago("PROCESADO");
        } else {
            log.warn("Método de pago no reconocido: {}. El pago se registrará sin procesar.", pago.getMetodoPago());
            pago.setEstadoPago("PENDIENTE");
        }

        return repository.save(pago);
    }

    /**
     * Resuelve, mediante el patrón Adapter, la implementación de {@link ProcesadorPago}
     * correspondiente al método de pago solicitado.
     */
    private ProcesadorPago resolverAdaptador(String metodoPago) {
        if (metodoPago == null) {
            return null;
        }
        return switch (metodoPago.toUpperCase()) {
            case "YAPE" -> new YapeAdapter(new PagoYape());
            case "PLIN" -> new PlinAdapter(new PagoPlin());
            case "VISA" -> new VisaAdapter(new PagoVisa());
            case "MASTERCARD" -> new MastercardAdapter(new PagoMastercard());
            case "BBVA" -> new BBVAAdapter(new PagoBBVA());
            case "BCP" -> new BCPAdapter(new PagoBCP());
            default -> null;
        };
    }

    @Override
    public Pago actualizar(Long id, Pago pago) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoPago(pago.getCodigoPago());
                    registro.setMonto(pago.getMonto());
                    registro.setFechaPago(pago.getFechaPago());
                    registro.setEstadoPago(pago.getEstadoPago());
                    registro.setMetodoPago(pago.getMetodoPago());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Pago no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}