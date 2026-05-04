package sistema_organizacion.sistema.usecases.tarea;
import sistema_organizacion.sistema.entities.Tarea;

public interface CrearTareaUseCase {
    Tarea ejecutar(CrearTareaCommand command);
}