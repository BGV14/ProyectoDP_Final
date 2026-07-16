// entity/Pago.java
package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoPago;

    @Positive(message = "El monto debe ser mayor a cero")
    private double monto;
    private String fechaPago;
    private String estadoPago;

    /** Método de pago solicitado: YAPE, PLIN, VISA, MASTERCARD, BBVA o BCP. */
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;
}