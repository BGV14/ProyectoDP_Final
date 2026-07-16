package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
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

    private double monto;
    private String fechaPago;
    private String estadoPago;

    /** Método de pago solicitado: YAPE, PLIN, VISA, MASTERCARD, BBVA o BCP. */
    private String metodoPago;
}