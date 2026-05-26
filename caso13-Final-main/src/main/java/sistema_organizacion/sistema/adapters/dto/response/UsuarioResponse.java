package sistema_organizacion.sistema.adapters.dto.response;

public class UsuarioResponse {
    private Long id;
    private String nombreCompleto;
    private String correo;
    private String username;
    private String rol;
    private String estado;
    private Long grupoId;
    private String grupoNombre;

    public Long getId()                 { return id; }
    public void setId(Long id)          { this.id = id; }
    public String getNombreCompleto()       { return nombreCompleto; }
    public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public String getCorreo()               { return correo; }
    public void setCorreo(String c)         { this.correo = c; }
    public String getUsername()             { return username; }
    public void setUsername(String u)       { this.username = u; }
    public String getRol()                  { return rol; }
    public void setRol(String r)            { this.rol = r; }
    public String getEstado()               { return estado; }
    public void setEstado(String e)         { this.estado = e; }
    public Long getGrupoId()                { return grupoId; }
    public void setGrupoId(Long grupoId)    { this.grupoId = grupoId; }
    public String getGrupoNombre()              { return grupoNombre; }
    public void setGrupoNombre(String grupoNombre) { this.grupoNombre = grupoNombre; }
}