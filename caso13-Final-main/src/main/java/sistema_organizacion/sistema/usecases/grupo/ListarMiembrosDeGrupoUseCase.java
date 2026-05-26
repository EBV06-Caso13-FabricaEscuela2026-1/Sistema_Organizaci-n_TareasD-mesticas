package sistema_organizacion.sistema.usecases.grupo;

import java.util.List;
import sistema_organizacion.sistema.entities.Usuario;

public interface ListarMiembrosDeGrupoUseCase {
    List<Usuario> ejecutar(Long grupoId);
}
