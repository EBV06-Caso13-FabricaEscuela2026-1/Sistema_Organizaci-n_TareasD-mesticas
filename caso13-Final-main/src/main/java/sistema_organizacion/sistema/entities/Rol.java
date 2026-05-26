package sistema_organizacion.sistema.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer id;
    
    @Column(name = "nombre_rol", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RolUsuario nombreRol; // ADMIN, USER

    public String name() {
        return nombreRol != null ? nombreRol.name() : "";
    }
}