package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.MetodoPago;
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
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}