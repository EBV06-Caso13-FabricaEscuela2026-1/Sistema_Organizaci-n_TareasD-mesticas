package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "estados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer id;
    
    @Column(name = "nombre_estado", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EstadoTarea nombreEstado; // PENDIENTE, EN_PROCESO, TERMINADA
    
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    public String name() {
        return nombreEstado != null ? nombreEstado.name() : "";
    }
}