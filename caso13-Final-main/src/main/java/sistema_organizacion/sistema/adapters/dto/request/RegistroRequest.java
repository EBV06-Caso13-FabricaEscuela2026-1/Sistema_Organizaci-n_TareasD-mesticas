package sistema_organizacion.sistema.adapters.dto.request;

public class RegistroRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String contrasena;
    private String rol;            // "ADMIN" o "USER" — CA-04-A HU-01

    public String getNombre()         { return nombre; }
    public void setNombre(String n)   { this.nombre = n; }
    public String getApellido()       { return apellido; }
    public void setApellido(String a) { this.apellido = a; }
    public String getCorreo()         { return correo; }
    public void setCorreo(String c)   { this.correo = c; }
    public String getUsername()       { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getContrasena()     { return contrasena; }
    public void setContrasena(String c) { this.contrasena = c; }
    public String getRol()            { return rol; }
    public void setRol(String r)      { this.rol = r; }
}