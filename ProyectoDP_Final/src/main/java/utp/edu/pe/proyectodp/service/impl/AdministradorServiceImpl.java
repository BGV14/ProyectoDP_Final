package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;
import utp.edu.pe.proyectodp.service.AdministradorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Override
    public List<Administrador> listar() {
        return administradorRepository.findAll();
    }

    @Override
    public Optional<Administrador> buscarPorId(Long id) {
        return administradorRepository.findById(id);
    }

    @Override
    public Administrador guardar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador actualizar(Long id, Administrador administrador) {
        return administradorRepository.findById(id)
                .map(admin -> {
                    admin.setCodigoAdministrador(administrador.getCodigoAdministrador());
                    admin.setUser(administrador.getUser());
                    admin.setPassword(administrador.getPassword());
                    return administradorRepository.save(admin);
                })
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new RuntimeException("Administrador no encontrado con id: " + id);
        }
        administradorRepository.deleteById(id);
    }
}