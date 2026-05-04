package sistema_organizacion.sistema.entities.exception;

public class GrupoFamiliarNoEncontradoException extends RuntimeException {
    public GrupoFamiliarNoEncontradoException(Long id) {
        super("Grupo familiar no encontrado con id: " + id);
    }
    public GrupoFamiliarNoEncontradoException(String id) {
        super("Grupo familiar no encontrado con id: " + id);
    }
}
