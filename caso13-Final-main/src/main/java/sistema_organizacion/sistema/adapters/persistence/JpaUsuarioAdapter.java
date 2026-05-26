package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.Rol;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.infrastructure.repositories.RolRepository;
import sistema_organizacion.sistema.infrastructure.repositories.UsuarioRepository;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;

@Component
public class JpaUsuarioAdapter implements UsuarioOutputPort {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public JpaUsuarioAdapter(UsuarioRepository usuarioRepository,
                             RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    @SuppressWarnings("null")
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Rol buscarRolPorNombre(RolUsuario nombreRol) {
        return rolRepository.findByNombreRol(nombreRol)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
    }

    @Override
    public boolean existeAdministradorPorGrupo(Long grupoId) {
        return usuarioRepository.existsByGrupoIdAndRolNombreRol(grupoId, RolUsuario.ADMIN);
    }

    @Override
    public List<Usuario> listarMiembrosPorGrupo(Long grupoId) {
        return usuarioRepository.findAllByGrupoId(grupoId);
    }
}
