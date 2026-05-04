package sistema_organizacion.sistema.entities.exception;

public class RolNoSeleccionadoException extends RuntimeException {
    public RolNoSeleccionadoException() {
        super("Debes seleccionar un rol para completar el registro");
    }
}