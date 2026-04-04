package sistema_organizacion.sistema.entities.exception;

public class NombreTareaDuplicadoException extends RuntimeException {
    public NombreTareaDuplicadoException(String nombre) {
        super("Ya existe una tarea con el nombre: " + nombre);
    }
}
