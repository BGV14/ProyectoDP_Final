package utp.edu.pe.proyectodp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI proyectoDpOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ProyectoDP Final API")
                        .description("Sistema de gestión académica: matrículas, cursos, notas, asistencia y pagos")
                        .version("v1.0.0")
                        .contact(new Contact().name("UTP - Proyecto DP"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }

}