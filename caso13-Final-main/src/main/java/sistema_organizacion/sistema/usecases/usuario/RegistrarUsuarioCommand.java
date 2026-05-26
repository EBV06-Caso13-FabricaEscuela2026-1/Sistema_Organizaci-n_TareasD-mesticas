package sistema_organizacion.sistema.usecases.usuario;

import sistema_organizacion.sistema.entities.RolUsuario;

public class RegistrarUsuarioCommand {
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String username;
    private final String contrasena;
    private final RolUsuario rol;

    public RegistrarUsuarioCommand(String nombre, String apellido, String correo,
                                    String username, String contrasena, RolUsuario rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsername() {
        return username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public RolUsuario getRol() {
        return rol;
    }
}