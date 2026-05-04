package sistema_organizacion.sistema.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity
@DiscriminatorValue("ADMIN")
@Getter @Setter
@NoArgsConstructor
public class JefeDeHogar extends Usuario {
    public JefeDeHogar(String nombre, String apellido, String correo,
                       String username, String contrasena, Rol rol) {
        super(nombre, apellido, correo, username, contrasena, rol);
    }
    public boolean esAdministrador() {
        return this.getRol().getNombreRol() == RolUsuario.ADMIN;
    }
}