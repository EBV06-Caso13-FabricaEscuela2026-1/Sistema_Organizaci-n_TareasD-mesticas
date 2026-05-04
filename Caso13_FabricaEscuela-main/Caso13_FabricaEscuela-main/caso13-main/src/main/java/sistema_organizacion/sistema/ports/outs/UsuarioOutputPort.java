package sistema_organizacion.sistema.ports.outs;

import java.util.Optional;
import sistema_organizacion.sistema.entities.Rol;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;

public interface UsuarioOutputPort {

    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorCorreo(String correo);

    Usuario guardar(Usuario usuario);

    Rol buscarRolPorNombre(RolUsuario nombreRol);

    boolean existeAdministradorPorGrupo(Long grupoId);
}