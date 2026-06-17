package com.duoc.reportservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reporte_incendio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteIncendio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String urlEvidencia;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        PENDIENTE,
        EN_COMBATE,
        CONTROLADO,
        EXTINGUIDO
    }
}
