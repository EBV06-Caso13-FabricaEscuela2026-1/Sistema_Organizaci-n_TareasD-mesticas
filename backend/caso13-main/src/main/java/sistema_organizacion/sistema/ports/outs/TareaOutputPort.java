package sistema_organizacion.sistema.ports.outs;

import java.util.List;
import java.util.Optional;
import sistema_organizacion.sistema.entities.Tarea;

public interface TareaOutputPort {
    Tarea guardar(Tarea tarea);
    Optional<Tarea> buscarPorId(String id);
    List<Tarea> buscarPorGrupoId(String grupoId);
}