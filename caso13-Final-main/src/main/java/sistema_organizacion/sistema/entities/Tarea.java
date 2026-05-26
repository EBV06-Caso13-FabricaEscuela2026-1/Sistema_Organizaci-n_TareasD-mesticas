package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;
@Entity
@Table(name = "tareas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;
    
    // Relación Many-to-One con Estado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;
    
    // Relación Many-to-One con GrupoFamiliar
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo", nullable = false)
    private GrupoFamiliar grupo;
    
    // Relación Many-to-One con Usuario (responsable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column(name = "nombre_miembro_asignado")
    private String nombreMiembroAsignado;
    
    // Relación One-to-Many con DetalleTarea
    @OneToMany(mappedBy = "tarea", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetalleTarea> detalles = new ArrayList<>();
    public Tarea(String titulo, String descripcion, LocalDate fechaLimite,
                    GrupoFamiliar grupo, Estado estado) {
        validarTitulo(titulo);
        validarDescripcion(descripcion);
        validarFechaLimite(fechaLimite);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.grupo = grupo;
        this.estado = estado;
    }
    public void actualizar(String nuevoTitulo, String nuevaDescripcion,
                            LocalDate nuevaFechaLimite) {
        validarTitulo(nuevoTitulo);
        validarDescripcion(nuevaDescripcion);
        validarFechaLimite(nuevaFechaLimite);
        this.titulo = nuevoTitulo;
        this.descripcion = nuevaDescripcion;
        this.fechaLimite = nuevaFechaLimite;
    }
    public void actualizarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }
    public void asignarResponsable(Usuario usuario, String nombreMiembro) {
        this.usuario = usuario;
        this.nombreMiembroAsignado = nombreMiembro;
    }
    private void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 3
                || titulo.trim().length() > 60) {
            throw new TareaInvalidaException(
                "El nombre debe tener entre 3 y 60 caracteres"
            );
        }
    }
    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) return;
        long palabras = descripcion.trim().split("\\s+").length;
        if (palabras > 100) {
            throw new TareaInvalidaException(
                "La descripción no debe superar 100 palabras"
            );
        }
    }
    private void validarFechaLimite(LocalDate fecha) {
        if (fecha == null || !fecha.isAfter(LocalDate.now())) {
            throw new TareaInvalidaException(
                "La fecha límite no puede estar en el pasado"
            );
        }
    }
    public Long getGrupoId() {
        return grupo != null ? grupo.getId() : null;
    }
    public Long getMiembroAsignadoId() {
        return usuario != null ? usuario.getId() : null;
    }
}