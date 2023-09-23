package Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pago" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "alumno_id")

    private Alumno alumno;
    private String fechaPago;
    private Double monto;
    private String tipoPago;
    private String estadoPago;
    private Double descuentoPago;
    private Double interesPago;
    private int cuotasPago;
    private String fechaVencimiento;



}
