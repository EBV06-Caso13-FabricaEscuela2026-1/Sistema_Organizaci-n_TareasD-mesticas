package sistema_organizacion.sistema.ports.outs;

import java.util.List;
import java.util.Optional;
import sistema_organizacion.sistema.entities.GrupoFamiliar;

public interface GrupoFamiliarOutputPort {

    GrupoFamiliar guardar(GrupoFamiliar grupo);

    Optional<GrupoFamiliar> buscarPorId(Long id);

    Optional<GrupoFamiliar> buscarPorCodigoAcceso(String codigo);

    List<GrupoFamiliar> listarTodos();
}