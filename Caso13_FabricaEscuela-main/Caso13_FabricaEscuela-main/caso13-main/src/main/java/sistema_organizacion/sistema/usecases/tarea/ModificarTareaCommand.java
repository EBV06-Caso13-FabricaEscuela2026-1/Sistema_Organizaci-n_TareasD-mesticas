package sistema_organizacion.sistema.usecases.tarea;

import java.time.LocalDate;

public class ModificarTareaCommand {
    private final Long tareaId;
    private final Long jefeId;
    private final String nuevoTitulo;
    private final String nuevaDescripcion;
    private final LocalDate nuevaFechaLimite;

    public ModificarTareaCommand(Long tareaId, Long jefeId,
                                  String nuevoTitulo, String nuevaDescripcion,
                                  LocalDate nuevaFechaLimite) {
        this.tareaId = tareaId;
        this.jefeId = jefeId;
        this.nuevoTitulo = nuevoTitulo;
        this.nuevaDescripcion = nuevaDescripcion;
        this.nuevaFechaLimite = nuevaFechaLimite;
    }

    public Long getTareaId()            { return tareaId; }
    public Long getJefeId()             { return jefeId; }
    public String getNuevoTitulo()       { return nuevoTitulo; }
    public String getNuevaDescripcion()  { return nuevaDescripcion; }
    public LocalDate getNuevaFechaLimite() { return nuevaFechaLimite; }
}