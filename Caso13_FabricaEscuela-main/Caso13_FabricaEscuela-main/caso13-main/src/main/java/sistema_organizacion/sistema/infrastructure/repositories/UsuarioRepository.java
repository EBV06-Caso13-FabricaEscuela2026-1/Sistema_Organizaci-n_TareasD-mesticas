package sistema_organizacion.sistema.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.grupo.id = :grupoId AND u.rol.nombreRol = :nombreRol")
    boolean existsByGrupoIdAndRolNombreRol(@Param("grupoId") Long grupoId, @Param("nombreRol") RolUsuario nombreRol);
}
