package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;
import sistema_organizacion.sistema.entities.exception.*;
@Entity
@Table(name = "grupos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id;
    
    @Column(name = "nombre_grupo", nullable = false)
    private String nombre;
    
    @Column(name = "codigo_acceso", unique = true, nullable = false)
    private String codigoAcceso;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
    
    // Relación uno-a-muchos con Usuarios (miembros del grupo)
    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Usuario> miembros = new ArrayList<>();
    
    // Relación uno-a-muchos con Tareas
    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tarea> tareas = new ArrayList<>();
    public GrupoFamiliar(String nombre, String codigoAcceso) {
        validarNombre(nombre);
        this.nombre = nombre;
        this.codigoAcceso = codigoAcceso;
        this.fechaCreacion = LocalDate.now();
        this.miembros = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 6
                || nombre.trim().length() > 25) {
            throw new NombreGrupoInvalidoException(
                "El nombre debe tener entre 6 y 25 caracteres"
            );
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9 ]+")) {
            throw new NombreGrupoInvalidoException(
                "El nombre solo permite letras, números, espacios, tildes y ñ"
            );
        }
    }
    public void agregarMiembro(Usuario miembro) {
        if (!miembros.contains(miembro)) {
            miembros.add(miembro);
            miembro.setGrupo(this);
        }
    }
    public void removerMiembro(Usuario miembro) {
        if (miembros.contains(miembro)) {
            miembros.remove(miembro);
            miembro.setGrupo(null);
        }
    }
}