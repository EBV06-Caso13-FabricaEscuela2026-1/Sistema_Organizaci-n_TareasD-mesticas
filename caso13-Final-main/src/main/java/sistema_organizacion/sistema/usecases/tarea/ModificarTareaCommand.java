package sistema_organizacion.sistema.usecases.tarea;

import java.time.LocalDate;

public class ModificarTareaCommand {
    private final Long tareaId;
    private final Long jefeId;
    private final String nuevoTitulo;
    private final String nuevaDescripcion;
    private final LocalDate nuevaFechaLimite;
    private final Long usuarioAsignadoId;
    private final boolean limpiarAsignacion;

    public ModificarTareaCommand(Long tareaId, Long jefeId,
                                  String nuevoTitulo, String nuevaDescripcion,
                                  LocalDate nuevaFechaLimite,
                                  Long usuarioAsignadoId,
                                  boolean limpiarAsignacion) {
        this.tareaId = tareaId;
        this.jefeId = jefeId;
        this.nuevoTitulo = nuevoTitulo;
        this.nuevaDescripcion = nuevaDescripcion;
        this.nuevaFechaLimite = nuevaFechaLimite;
        this.usuarioAsignadoId = usuarioAsignadoId;
        this.limpiarAsignacion = limpiarAsignacion;
    }

    public Long getTareaId()              { return tareaId; }
    public Long getJefeId()               { return jefeId; }
    public String getNuevoTitulo()         { return nuevoTitulo; }
    public String getNuevaDescripcion()    { return nuevaDescripcion; }
    public LocalDate getNuevaFechaLimite() { return nuevaFechaLimite; }
    public Long getUsuarioAsignadoId()     { return usuarioAsignadoId; }
    public boolean isLimpiarAsignacion()   { return limpiarAsignacion; }
}