package sistema_organizacion.sistema.adapters.dto.request;

public class LoginRequest {
    private String correo;
    private String contrasena;

    public String getCorreo()           { return correo; }
    public void setCorreo(String c)     { this.correo = c; }
    public String getContrasena()       { return contrasena; }
    public void setContrasena(String c) { this.contrasena = c; }
}
