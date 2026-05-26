package sistema_organizacion.sistema.entities.exception;

public class CamposObligatoriosException extends RuntimeException {
    public CamposObligatoriosException() {
        super("Los campos correo y contraseña son obligatorios");
    }
}