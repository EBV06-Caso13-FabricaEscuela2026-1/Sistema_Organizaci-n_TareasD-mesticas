package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;

@Component
public class InMemoryTareaAdapter implements TareaOutputPort {

    private final Map<String, Tarea> tareas = new ConcurrentHashMap<>();

    @Override
    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null || tarea.getId().isBlank()) {
            tarea.setId(UUID.randomUUID().toString());
        }
        tareas.put(tarea.getId(), tarea);
        return tarea;
    }

    @Override
    public Optional<Tarea> buscarPorId(String id) {
        return Optional.ofNullable(tareas.get(id));
    }

    @Override
    public List<Tarea> buscarPorGrupoId(String grupoId) {
        return tareas.values().stream()
            .filter(tarea -> tarea.getGrupoId().equals(grupoId))
            .toList();
    }
}
