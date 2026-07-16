package utp.edu.pe.proyectodp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.repository.AdministradorRepository;

@Component
@RequiredArgsConstructor
public class DatosPruebaInitializer implements CommandLineRunner {

    private final AdministradorRepository repository;
    private final PasswordEncoder encoder;

    @Value("${app.admin.user}")
    private String adminUser;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {

        if (repository.findAll().isEmpty()) {

            Administrador admin = Administrador.builder()
                    .user(adminUser)
                    .password(encoder.encode(adminPassword))
                    .codigoAdministrador("ADM001")
                    .build();

            repository.save(admin);
        }
    }
}