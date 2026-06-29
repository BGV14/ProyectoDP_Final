package utp.edu.pe.proyectodp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Persona {

    private int idPersona;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;


}
