package utp.edu.pe.proyectodp.service.impl;

import jakarta.annotation.PostConstruct;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.PagoRepository;
import utp.edu.pe.proyectodp.service.PagoService;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;
import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;
import utp.edu.pe.proyectodp.service.pattern.singlenton.GeneradorCodigo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;
    private final List<ProcesadorPago> procesadoresDisponibles;
    private Map<String, ProcesadorPago> procesadores;

    @PostConstruct
    public void indexarProcesadores() {
        procesadores = procesadoresDisponibles.stream()
                .collect(Collectors.toMap(ProcesadorPago::getCodigo, p -> p));
    }

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
            throw new IllegalStateException("El sistema está¡ en mantenimiento. Intente mÃ¡s tarde.");
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
            log.warn("Método de pago no reconocido: {}. El pago se registrará¡ sin procesar.", pago.getMetodoPago());
            pago.setEstadoPago("PENDIENTE");
        }

        return repository.save(pago);
    }

    private ProcesadorPago resolverAdaptador(String metodoPago) {
        if (metodoPago == null) {
            return null;
        }
        return procesadores.get(metodoPago.toUpperCase());
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