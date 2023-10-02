package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cuota")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuota;

    @ManyToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;
    private Double montoInicio; //monto de la cuota antes  de aplicar cualquier descuento o interes
    private Double montoFinal; //monto de la cuota luego de aplicar todo tipo de cambio

    @Enumerated(EnumType.STRING)
    private EstadoCuota estadoCuota;

    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    private Double interesAplicado = 0.0;

    public enum EstadoCuota{
        PENDIENTE,
        PAGADA,
        ATRASADA
    }

}
