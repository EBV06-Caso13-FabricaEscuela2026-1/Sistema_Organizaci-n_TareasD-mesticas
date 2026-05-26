package sistema_organizacion.sistema.usecases.tarea;

import sistema_organizacion.sistema.entities.Tarea;

public interface ActualizarEstadoTareaUseCase {
    Tarea ejecutar(ActualizarEstadoTareaCommand command);
}
