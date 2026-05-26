package sistema_organizacion.sistema.entities.exception;

public class CodigoAccesoInvalidoException extends RuntimeException {
    public CodigoAccesoInvalidoException() {
        super("El código de acceso es inválido");
    }
}