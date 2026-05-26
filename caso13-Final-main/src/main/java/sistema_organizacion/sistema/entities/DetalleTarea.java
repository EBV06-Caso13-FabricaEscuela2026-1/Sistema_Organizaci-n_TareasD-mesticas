package sistema_organizacion.sistema.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore; // IMPORTANTE

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_tareas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tarea", nullable = false)
    @JsonIgnore   // AQUÍ VA
    private Tarea tarea;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    public DetalleTarea(Tarea tarea, String descripcion, String observacion) {
        this.tarea = tarea;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.fechaActualizacion = LocalDate.now();
    }
}