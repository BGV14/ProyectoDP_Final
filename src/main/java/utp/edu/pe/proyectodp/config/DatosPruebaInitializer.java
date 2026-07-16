package utp.edu.pe.proyectodp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void run(String... args) {

        if(repository.findAll().isEmpty()) {

            Administrador admin = Administrador.builder()
                    .user("admin")
                    .password(encoder.encode("1234"))
                    .codigoAdministrador("ADM001")
                    .build();

            repository.save(admin);
        }
    }
}