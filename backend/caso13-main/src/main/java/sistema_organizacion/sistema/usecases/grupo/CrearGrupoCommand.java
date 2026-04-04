package sistema_organizacion.sistema.usecases.grupo;

public class CrearGrupoCommand {
    private final String nombre;
    private final String jefeId;

    public CrearGrupoCommand(String nombre, String jefeId) {
        this.nombre = nombre;
        this.jefeId = jefeId;
    }

    public String getNombre()  { return nombre; }
    public String getJefeId()  { return jefeId; }
}