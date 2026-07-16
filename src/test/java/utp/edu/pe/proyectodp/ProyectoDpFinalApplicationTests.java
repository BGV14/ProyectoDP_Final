package utp.edu.pe.proyectodp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utp.edu.pe.proyectodp.service.AuthService;

@SpringBootTest
class ProyectoDpFinalApplicationTests {

	@Autowired
	private AuthService authService;

	@Test
	void loginTest() {
		boolean resultado = authService.login("admin", "1234");
		System.out.println("Login exitoso: " + resultado);
	}

}
