package utp.edu.pe.proyectodp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;

@Component
@RequiredArgsConstructor
public class DatosPruebaInitializer implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;

    @Override
    public void run(String... args) {
        if (administradorRepository.findAll().isEmpty()) {
            Administrador admin = Administrador.builder()
                    .codigoAdministrador("ADM-0001")
                    .user("admin")
                    .password("1234")
                    .build();
            administradorRepository.save(admin);
        }
    }
}