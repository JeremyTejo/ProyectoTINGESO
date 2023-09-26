package Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pago" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    private LocalDate fechaPago;
    private Double monto;

    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;
    private Double descuentoPago;
    private Double interesPago;
    private int cuotasPago;
    private String fechaVencimiento;
    private int cuotasPendientes;

    public enum TipoPago{
        CONTADO, CUOTAS
    }

    public enum EstadoPago{
        PAGADO, PENDIENTE, ATRASADO
    }
}
