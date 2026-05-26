package sistema_organizacion.sistema.entities.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String id) {
        super("Usuario no encontrado con id: " + id);
    }
}
