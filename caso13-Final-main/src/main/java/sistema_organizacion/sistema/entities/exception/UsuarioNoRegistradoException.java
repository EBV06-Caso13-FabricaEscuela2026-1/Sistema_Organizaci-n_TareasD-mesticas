package sistema_organizacion.sistema.entities.exception;

public class UsuarioNoRegistradoException extends RuntimeException {
    public UsuarioNoRegistradoException() {
        super("Usuario no registrado");
    }
}