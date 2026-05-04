package sistema_organizacion.sistema.usecases.tarea;

import sistema_organizacion.sistema.entities.Tarea;

public interface ModificarTareaUseCase {
    Tarea ejecutar(ModificarTareaCommand command);
}