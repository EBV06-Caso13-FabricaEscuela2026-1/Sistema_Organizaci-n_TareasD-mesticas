package sistema_organizacion.sistema.usecases.grupo;

public class IngresarGrupoCommand {

    private final Long usuarioId;
    private final String codigoAcceso;

    public IngresarGrupoCommand(Long usuarioId, String codigoAcceso) {
        this.usuarioId = usuarioId;
        this.codigoAcceso = codigoAcceso;
    }

    public Long getUsuarioId() { return usuarioId; }
    public String getCodigoAcceso() { return codigoAcceso; }
}