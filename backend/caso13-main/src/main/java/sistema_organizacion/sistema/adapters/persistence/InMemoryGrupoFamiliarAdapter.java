package sistema_organizacion.sistema.adapters.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;

@Component
public class InMemoryGrupoFamiliarAdapter implements GrupoFamiliarOutputPort {

    private final Map<String, GrupoFamiliar> grupos = new ConcurrentHashMap<>();

    @Override
    public GrupoFamiliar guardar(GrupoFamiliar grupo) {
        if (grupo.getId() == null || grupo.getId().isBlank()) {
            grupo.setId(UUID.randomUUID().toString());
        }
        grupos.put(grupo.getId(), grupo);
        return grupo;
    }

    @Override
    public Optional<GrupoFamiliar> buscarPorId(String id) {
        return Optional.ofNullable(grupos.get(id));
    }

    @Override
    public Optional<GrupoFamiliar> buscarPorCodigo(String codigo) {
        return grupos.values().stream()
            .filter(grupo -> grupo.getCodigoAcceso().equalsIgnoreCase(codigo))
            .findFirst();
    }
}
