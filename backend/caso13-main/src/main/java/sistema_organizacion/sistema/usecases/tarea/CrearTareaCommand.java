package sistema_organizacion.sistema.usecases.tarea;
import java.time.LocalDate;

public class CrearTareaCommand {
    private final String titulo;
    private final String descripcion;
    private final LocalDate fechaLimite;
    private final String grupoId;
    private final String jefeId;

    public CrearTareaCommand(String titulo, String descripcion,
                              LocalDate fechaLimite,
                              String grupoId, String jefeId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.grupoId = grupoId;
        this.jefeId = jefeId;
    }

    public String getTitulo()         { return titulo; }
    public String getDescripcion()    { return descripcion; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public String getGrupoId()        { return grupoId; }
    public String getJefeId()         { return jefeId; }
}