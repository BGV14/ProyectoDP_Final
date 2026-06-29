package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.repository.PagoRepository;
import utp.edu.pe.proyectodp.service.PagoService;

import java.util.List;
import java.util.Optional;

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
        return repository.save(pago);
    }

    @Override
    public Pago actualizar(Long id, Pago pago) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoPago(pago.getCodigoPago());
                    registro.setMonto(pago.getMonto());
                    registro.setFechaPago(pago.getFechaPago());
                    registro.setEstadoPago(pago.getEstadoPago());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}