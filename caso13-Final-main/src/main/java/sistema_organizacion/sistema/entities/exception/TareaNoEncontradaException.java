package sistema_organizacion.sistema.entities.exception;

public class TareaNoEncontradaException extends RuntimeException {
    public TareaNoEncontradaException(Long id) {
        super("Tarea no encontrada con id: " + id);
    }
    public TareaNoEncontradaException(String id) {
        super("Tarea no encontrada con id: " + id);
    }
}
