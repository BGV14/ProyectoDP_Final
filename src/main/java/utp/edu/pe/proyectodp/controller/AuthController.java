package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.proyectodp.controller.dto.LoginRequest;
import utp.edu.pe.proyectodp.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        boolean autenticado = authService.login(request.user(), request.password());
        if (autenticado) {
            return ResponseEntity.ok(Map.of("mensaje", "Autenticación exitosa"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("mensaje", "Usuario o contraseña incorrectos"));
    }
}
