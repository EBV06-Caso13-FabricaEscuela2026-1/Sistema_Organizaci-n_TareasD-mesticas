package sistema_organizacion.sistema.adapters.dto.response;

public class TareaResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private String fechaLimite;
    private String estado;           // CA-04 HU-12
    private String miembroAsignado;  // CA-05 y CA-03 HU-12
    private Long grupoId;

    public Long getId()                   { return id; }
    public void setId(Long id)            { this.id = id; }
    public String getTitulo()             { return titulo; }
    public void setTitulo(String t)       { this.titulo = t; }
    public String getDescripcion()        { return descripcion; }
    public void setDescripcion(String d)  { this.descripcion = d; }
    public String getFechaLimite()        { return fechaLimite; }
    public void setFechaLimite(String f)  { this.fechaLimite = f; }
    public String getEstado()             { return estado; }
    public void setEstado(String e)       { this.estado = e; }
    public String getMiembroAsignado()    { return miembroAsignado; }
    public void setMiembroAsignado(String m) { this.miembroAsignado = m; }
    public Long getGrupoId()              { return grupoId; }
    public void setGrupoId(Long g)        { this.grupoId = g; }
}
