package sistema_organizacion.sistema.usecases.grupo;

public class CrearGrupoCommand {

    private final String nombre;
    private final Long jefeId;

    public CrearGrupoCommand(String nombre, Long jefeId) {
        this.nombre = nombre;
        this.jefeId = jefeId;
    }

    public String getNombre() { return nombre; }
    public Long getJefeId() { return jefeId; }
}