package utp.edu.pe.proyectodp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdministradorRepository administradorRepository;

    public boolean login(String user, String password) {
        return administradorRepository.findAll().stream()
                .filter(admin -> admin.getUser().equals(user) && admin.getPassword().equals(password))
                .findFirst()
                .map(admin -> {
                    var sesion = SesionSistema.getInstancia();
                    sesion.setUsuario(admin.getUser());
                    sesion.setRol("ADMINISTRADOR");
                    sesion.setAutenticado(true);
                    log.info("Inicio de sesión exitoso para el usuario: {}", admin.getUser());
                    return true;
                })
                .orElseGet(() -> {
                    log.warn("Intento de login fallido para el usuario: {}", user);
                    return false;
                });
    }
}