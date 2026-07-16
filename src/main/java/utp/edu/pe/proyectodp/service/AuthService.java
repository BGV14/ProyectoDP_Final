package utp.edu.pe.proyectodp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean login(String user, String password) {

        List<Administrador> administradores = administradorRepository.findAll();

        administradores.forEach(admin -> {
            log.info("BD usuario: " + admin.getUser());
            log.info("BD password: " + admin.getPassword());
        });

        return administradores.stream()
                .filter(admin -> admin.getUser().equals(user)
                        && passwordEncoder.matches(password, admin.getPassword()))
                .findFirst()
                .map(admin -> {
                    SesionSistema.getInstancia().setUsuario(admin.getUser());
                    SesionSistema.getInstancia().setRol("ADMINISTRADOR");
                    SesionSistema.getInstancia().setAutenticado(true);
                    return true;
                })
                .orElse(false);
    }
}