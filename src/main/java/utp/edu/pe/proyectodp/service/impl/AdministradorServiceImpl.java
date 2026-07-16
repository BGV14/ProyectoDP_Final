package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;
import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;
import utp.edu.pe.proyectodp.service.AdministradorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

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
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        administrador.setPassword(passwordEncoder.encode(administrador.getPassword()));
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador actualizar(Long id, Administrador administrador) {
        return administradorRepository.findById(id)
                .map(admin -> {
                    admin.setCodigoAdministrador(administrador.getCodigoAdministrador());
                    admin.setUser(administrador.getUser());
                    admin.setPassword(passwordEncoder.encode(administrador.getPassword()));
                    return administradorRepository.save(admin);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Administrador no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Administrador no encontrado con id: " + id);
        }
        administradorRepository.deleteById(id);
    }
}