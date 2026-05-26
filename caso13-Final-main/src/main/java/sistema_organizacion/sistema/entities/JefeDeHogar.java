package sistema_organizacion.sistema.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity
@DiscriminatorValue("ADMIN")
@Getter @Setter
@NoArgsConstructor
public class JefeDeHogar extends Usuario {

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_miembro")
    private EstadoMiembro estadoMiembro = EstadoMiembro.SIN_GRUPO;

    public JefeDeHogar(String nombre, String apellido, String correo,
                       String username, String contrasena, Rol rol) {
        super(nombre, apellido, correo, username, contrasena, rol);
        this.estadoMiembro = EstadoMiembro.SIN_GRUPO;
    }

    public void crearGrupo(GrupoFamiliar grupo) {
        this.setGrupo(grupo);
        this.estadoMiembro = EstadoMiembro.ACTIVO;
    }

    public boolean esAdministrador() {
        return this.getRol().getNombreRol() == RolUsuario.ADMIN;
    }
}