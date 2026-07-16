package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Boleta;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
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
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

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
                .orElseThrow(() -> new RecursoNoEncontradoException("Boleta no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}