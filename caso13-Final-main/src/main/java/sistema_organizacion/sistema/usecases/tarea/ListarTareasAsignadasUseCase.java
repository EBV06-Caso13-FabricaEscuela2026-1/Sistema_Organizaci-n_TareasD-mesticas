package sistema_organizacion.sistema.usecases.tarea;

import java.util.List;
import sistema_organizacion.sistema.entities.Tarea;

public interface ListarTareasAsignadasUseCase {
    List<Tarea> ejecutar(Long miembroId);
}
