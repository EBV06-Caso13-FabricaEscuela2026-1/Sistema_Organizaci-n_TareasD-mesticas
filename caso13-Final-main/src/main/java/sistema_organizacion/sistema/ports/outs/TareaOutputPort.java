package sistema_organizacion.sistema.ports.outs;

import java.util.List;
import java.util.Optional;

import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.Tarea;

public interface TareaOutputPort {

    Tarea guardar(Tarea tarea);

    Optional<Tarea> buscarPorId(Long id);

    Estado buscarEstadoPorNombre(EstadoTarea nombreEstado);

    List<Tarea> findByGrupoId(Long grupoId);

    List<Tarea> findByUsuarioId(Long usuarioId);

    void eliminar(Long id);
}