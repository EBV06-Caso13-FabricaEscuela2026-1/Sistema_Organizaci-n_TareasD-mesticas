package sistema_organizacion.sistema.adapters.dto.response;

public class SesionResponse {
    private Long id;
    private String nombreCompleto;
    private String rol;
    private String redireccion;
    private Long grupoId;
    private String grupo;
    private String codigoAcceso;

    public Long getId()                             { return id; }
    public void setId(Long id)                      { this.id = id; }
    public String getNombreCompleto()               { return nombreCompleto; }
    public void setNombreCompleto(String n)         { this.nombreCompleto = n; }
    public String getRol()                          { return rol; }
    public void setRol(String rol)                  { this.rol = rol; }
    public String getRedireccion()                  { return redireccion; }
    public void setRedireccion(String redireccion)  { this.redireccion = redireccion; }
    public Long getGrupoId()                        { return grupoId; }
    public void setGrupoId(Long grupoId)            { this.grupoId = grupoId; }
    public String getGrupo()                        { return grupo; }
    public void setGrupo(String grupo)              { this.grupo = grupo; }
    public String getCodigoAcceso()                 { return codigoAcceso; }
    public void setCodigoAcceso(String codigo)      { this.codigoAcceso = codigo; }
}
