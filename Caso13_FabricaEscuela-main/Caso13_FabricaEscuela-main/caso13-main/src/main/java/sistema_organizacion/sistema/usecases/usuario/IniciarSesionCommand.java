package sistema_organizacion.sistema.usecases.usuario;

public class IniciarSesionCommand {
    private final String correo;
    private final String contrasena;

    public IniciarSesionCommand(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo()     { return correo; }
    public String getContrasena() { return contrasena; }
}
