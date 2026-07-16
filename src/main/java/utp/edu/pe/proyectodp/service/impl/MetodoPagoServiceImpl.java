package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.MetodoPago;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.MetodoPagoRepository;
import utp.edu.pe.proyectodp.service.MetodoPagoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository repository;

    @Override
    public List<MetodoPago> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<MetodoPago> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public MetodoPago guardar(MetodoPago metodoPago) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(metodoPago);
    }

    @Override
    public MetodoPago actualizar(Long id, MetodoPago metodoPago) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setTipoPago(metodoPago.getTipoPago());
                    registro.setDescripcion(metodoPago.getDescripcion());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("MÃ©todo de pago no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}