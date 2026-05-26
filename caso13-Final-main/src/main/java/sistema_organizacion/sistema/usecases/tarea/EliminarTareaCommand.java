package sistema_organizacion.sistema.usecases.tarea;

public class EliminarTareaCommand {
    private final Long tareaId;
    private final Long jefeId;

    public EliminarTareaCommand(Long tareaId, Long jefeId) {
        this.tareaId = tareaId;
        this.jefeId = jefeId;
    }

    public Long getTareaId() { return tareaId; }
    public Long getJefeId()  { return jefeId; }
}
