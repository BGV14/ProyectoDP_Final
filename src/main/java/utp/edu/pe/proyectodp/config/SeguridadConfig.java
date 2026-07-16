package utp.edu.pe.proyectodp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Antes, el password del Administrador se guardaba y se comparaba en texto
 * plano (ver DatosPruebaInitializer y AuthService). Este bean permite
 * hashear el password al guardarlo y verificarlo de forma segura al hacer
 * login, sin necesidad de traer todo Spring Security (solo se usa el
 * módulo spring-security-crypto).
 */
@Configuration
public class SeguridadConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}