package sistema_organizacion.sistema.ports.outs;
import java.util.Optional;
import sistema_organizacion.sistema.entities.Usuario;

public interface UsuarioOutputPort {
    Optional<Usuario> buscarPorId(String id);
    Optional<Usuario> buscarPorCorreo(String correo);
    Usuario guardar(Usuario usuario);
}
