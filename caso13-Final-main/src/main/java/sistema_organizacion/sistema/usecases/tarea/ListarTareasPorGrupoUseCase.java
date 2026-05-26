package sistema_organizacion.sistema.usecases.tarea;

import java.util.List;
import org.springframework.stereotype.Service;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;

@Service
public class ListarTareasPorGrupoUseCase {

    private final TareaOutputPort tareaOutputPort;

    public ListarTareasPorGrupoUseCase(TareaOutputPort tareaOutputPort) {
        this.tareaOutputPort = tareaOutputPort;
    }

    public List<Tarea> ejecutar(Long grupoId) {
        return tareaOutputPort.findByGrupoId(grupoId);
    }
}