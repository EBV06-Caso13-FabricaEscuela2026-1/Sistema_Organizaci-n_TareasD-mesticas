package sistema_organizacion.sistema.entities.exception;

public class AdministradorYaExisteException extends RuntimeException {
    public AdministradorYaExisteException() {
        super("Este hogar ya cuenta con un administrador. "
            + "Debes registrarte como miembro del hogar");
    }
}
