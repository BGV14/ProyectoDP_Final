package utp.edu.pe.proyectodp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean login(String user, String password) {

        return administradorRepository.findAll().stream()
                .peek(admin -> {
                    System.out.println("BD usuario: " + admin.getUser());
                    System.out.println("BD password: " + admin.getPassword());
                })
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