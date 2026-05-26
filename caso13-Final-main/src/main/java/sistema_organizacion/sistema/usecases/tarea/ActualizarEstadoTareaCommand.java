package sistema_organizacion.sistema.usecases.tarea;

public class ActualizarEstadoTareaCommand {
    private final Long tareaId;
    private final Long miembroId;
    private final String nuevoEstado;

    public ActualizarEstadoTareaCommand(Long tareaId, Long miembroId, String nuevoEstado) {
        this.tareaId = tareaId;
        this.miembroId = miembroId;
        this.nuevoEstado = nuevoEstado;
    }

    public Long getTareaId()      { return tareaId; }
    public Long getMiembroId()    { return miembroId; }
    public String getNuevoEstado() { return nuevoEstado; }
}
