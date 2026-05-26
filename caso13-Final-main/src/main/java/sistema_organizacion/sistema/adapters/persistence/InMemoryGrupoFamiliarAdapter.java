package sistema_organizacion.sistema.adapters.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;


public class InMemoryGrupoFamiliarAdapter implements GrupoFamiliarOutputPort {

    private final Map<Long, GrupoFamiliar> grupos = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public GrupoFamiliar guardar(GrupoFamiliar grupo) {
        if (grupo.getId() == null) {
            grupo.setId(idGenerator.getAndIncrement());
        }
        grupos.put(grupo.getId(), grupo);
        return grupo;
    }

    @Override
    public Optional<GrupoFamiliar> buscarPorId(Long id) {
        return Optional.ofNullable(grupos.get(id));
    }

    @Override
    public Optional<GrupoFamiliar> buscarPorCodigoAcceso(String codigo) {
        return grupos.values().stream()
            .filter(grupo -> grupo.getCodigoAcceso().equalsIgnoreCase(codigo))
            .findFirst();
    }

    @Override
    public List<GrupoFamiliar> listarTodos() {
        return new ArrayList<>(grupos.values());
    }
}
