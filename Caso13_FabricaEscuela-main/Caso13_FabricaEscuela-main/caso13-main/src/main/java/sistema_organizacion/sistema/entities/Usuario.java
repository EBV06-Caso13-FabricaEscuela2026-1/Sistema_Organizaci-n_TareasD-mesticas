package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import sistema_organizacion.sistema.entities.exception.*;
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "apellido", nullable = false)
    private String apellido;
    
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;
    
    @Column(name = "usuario", unique = true, nullable = false)
    private String username;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;
    
    // Relación Many-to-One con Rol
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
    
    // Relación Many-to-One con GrupoFamiliar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo")
    private GrupoFamiliar grupo;
    
    // Relación One-to-Many con Tareas (tareas asignadas)
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarea> tareasAsignadas = new ArrayList<>();
    public Usuario(String nombre, String apellido, String correo,
                    String username, String contrasena, Rol rol) {
        validarCorreo(correo);
        validarContrasena(contrasena);
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaRegistro = LocalDate.now();
    }
    private void validarCorreo(String correo) {
        if (correo == null || !correo.trim().matches("^[a-zA-Z0-9._%+\\-]+@gmail\\.com$")) {
            throw new CorreoInvalidoException(
                "El correo electrónico debe ser una cuenta @gmail.com válida"
            );
        }
    }
    private void validarContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() > 10
                || !contrasena.matches("[a-zA-Z0-9@#$%&*]+")) {
            throw new ContrasenaInvalidaException(
                "La contraseña debe tener máximo 10 caracteres"
            );
        }
    }
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}