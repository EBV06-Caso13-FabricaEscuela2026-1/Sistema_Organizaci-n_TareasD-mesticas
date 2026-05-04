package sistema_organizacion.sistema.usecases.tarea;
import sistema_organizacion.sistema.entities.Tarea;

public interface VerDetalleTareaUseCase {
    Tarea ejecutar(Long tareaId, Long grupoId);
}