package utp.edu.pe.proyectodp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utp.edu.pe.proyectodp.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProyectoDpFinalApplicationTests {

	@Autowired
	private AuthService authService;

	@Test
	void contextLoads() {
	}

	@Test
	void loginConCredencialesCorrectas() {
		boolean resultado = authService.login("admin", "1234");
		assertTrue(resultado, "El login debería ser exitoso con las credenciales correctas");
	}

	@Test
	void loginConCredencialesIncorrectas() {
		boolean resultado = authService.login("admin", "clave_mala");
		assertFalse(resultado, "El login debería fallar con una contraseña incorrecta");
	}
}
