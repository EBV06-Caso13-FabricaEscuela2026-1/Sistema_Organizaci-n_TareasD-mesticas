package sistema_organizacion.sistema.infrastructure.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_organizacion.sistema.entities.GrupoFamiliar;
import java.util.Optional;
@Repository
public interface GrupoFamiliarRepository extends JpaRepository<GrupoFamiliar, Long> {
    Optional<GrupoFamiliar> findByCodigoAcceso(String codigoAcceso);
    Optional<GrupoFamiliar> findByNombre(String nombre);
}