package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Distrito;
import utp.edu.pe.proyectodp.repository.DistritoRepository;
import utp.edu.pe.proyectodp.service.DistritoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistritoServiceImpl implements DistritoService {

    private final DistritoRepository repository;

    @Override
    public List<Distrito> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Distrito> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Distrito guardar(Distrito distrito) {
        return repository.save(distrito);
    }

    @Override
    public Distrito actualizar(Long id, Distrito distrito) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoDistrito(distrito.getCodigoDistrito());
                    registro.setNombreDistrito(distrito.getNombreDistrito());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}