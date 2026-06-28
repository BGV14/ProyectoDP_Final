package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Boleta;
import utp.edu.pe.proyectodp.repository.BoletaRepository;
import utp.edu.pe.proyectodp.service.BoletaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository repository;

    @Override
    public List<Boleta> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Boleta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boleta guardar(Boleta boleta) {
        return repository.save(boleta);
    }

    @Override
    public Boleta actualizar(Long id, Boleta boleta) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNumeroBoleta(boleta.getNumeroBoleta());
                    registro.setFechaEmision(boleta.getFechaEmision());
                    registro.setTotal(boleta.getTotal());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}