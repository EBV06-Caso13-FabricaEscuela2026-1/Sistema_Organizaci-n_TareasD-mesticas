package sistema_organizacion.sistema.infrastructure.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_organizacion.sistema.entities.Rol;
import sistema_organizacion.sistema.entities.RolUsuario;

import java.util.Optional;
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombreRol(RolUsuario nombreRol);
}