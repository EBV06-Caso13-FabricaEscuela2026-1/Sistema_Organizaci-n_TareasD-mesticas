package sistema_organizacion.sistema.entities;
import jakarta.persistence.*;
import lombok.*;
import sistema_organizacion.sistema.entities.exception.MiembroYaEnGrupoException;
@Entity
@DiscriminatorValue("USER")
@Getter
 @Setter
@NoArgsConstructor
public class MiembroHogar extends Usuario {
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_miembro")
    private EstadoMiembro estadoMiembro = EstadoMiembro.SIN_GRUPO;
    public MiembroHogar(String nombre, String apellido, String correo,
                        String username, String contrasena, Rol rol) {
        super(nombre, apellido, correo, username, contrasena, rol);
        this.estadoMiembro = EstadoMiembro.SIN_GRUPO;
    }
    public void asignarGrupo(GrupoFamiliar grupo) {
        if (this.getGrupo() != null) {
            throw new MiembroYaEnGrupoException(
                "Ya perteneces a un grupo familiar"
            );
        }
        this.setGrupo(grupo);
        this.estadoMiembro = EstadoMiembro.ACTIVO;
    }
    public boolean tieneGrupo() {
        return this.getGrupo() != null;
    }
}