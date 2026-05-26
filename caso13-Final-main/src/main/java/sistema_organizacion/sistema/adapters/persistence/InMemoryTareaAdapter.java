package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;

public class InMemoryTareaAdapter implements TareaOutputPort {

    private final Map<Long, Tarea> tareas = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(idGenerator.getAndIncrement());
        }
        tareas.put(tarea.getId(), tarea);
        return tarea;
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        return Optional.ofNullable(tareas.get(id));
    }

    @Override
    public Estado buscarEstadoPorNombre(EstadoTarea nombreEstado) {
        return null;
    }

    @Override
    public List<Tarea> findByGrupoId(Long grupoId) {
        return tareas.values().stream()
            .filter(tarea -> tarea.getGrupo().getId().equals(grupoId))
            .toList();
    }

    @Override
    public List<Tarea> findByUsuarioId(Long usuarioId) {
        return tareas.values().stream()
            .filter(t -> t.getUsuario() != null && t.getUsuario().getId().equals(usuarioId))
            .toList();
    }

    @Override
    public void eliminar(Long id) {
        tareas.remove(id);
    }
}