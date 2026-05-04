package sistema_organizacion.sistema.infrastructure.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_organizacion.sistema.entities.DetalleTarea;

@Repository
public interface DetalleTareaRepository extends JpaRepository<DetalleTarea, Long> {

    List<DetalleTarea> findByTarea_Id(Long tareaId);

}