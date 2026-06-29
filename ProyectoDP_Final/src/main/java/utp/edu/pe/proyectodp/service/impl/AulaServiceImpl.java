package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Aula;
import utp.edu.pe.proyectodp.repository.AulaRepository;
import utp.edu.pe.proyectodp.service.AulaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AulaServiceImpl implements AulaService {

    private final AulaRepository repository;

    @Override
    public List<Aula> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Aula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Aula guardar(Aula aula) {
        return repository.save(aula);
    }

    @Override
    public Aula actualizar(Long id, Aula aula) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoAula(aula.getCodigoAula());
                    registro.setNumero(aula.getNumero());
                    registro.setCapacidad(aula.getCapacidad());
                    registro.setPabellon(aula.getPabellon());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}