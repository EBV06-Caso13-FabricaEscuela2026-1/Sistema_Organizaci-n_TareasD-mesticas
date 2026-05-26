package sistema_organizacion.sistema.entities.exception;

public class AccesoDenegadoException extends RuntimeException {
    public AccesoDenegadoException() {
        super("No tiene permisos para realizar esta acción");
    }
}