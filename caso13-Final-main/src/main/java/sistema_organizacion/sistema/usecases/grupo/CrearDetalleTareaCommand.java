package sistema_organizacion.sistema.usecases.grupo;

import lombok.Getter;

@Getter
public class CrearDetalleTareaCommand {
    private final Long tareaId;
    private final String descripcion;
    private final String observacion;

    public CrearDetalleTareaCommand(Long tareaId, String descripcion, String observacion) {
        this.tareaId = tareaId;
        this.descripcion = descripcion;
        this.observacion = observacion;
    }
}