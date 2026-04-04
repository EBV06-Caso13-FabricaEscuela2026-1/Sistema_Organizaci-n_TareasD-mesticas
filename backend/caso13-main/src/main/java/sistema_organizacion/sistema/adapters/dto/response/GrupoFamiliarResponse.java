package sistema_organizacion.sistema.adapters.dto.response;

public class GrupoFamiliarResponse {
    private String id;
    private String nombre;
    private String codigoAcceso;    // CA-03-B HU-02: se muestra al crear
    private String fechaCreacion;

    public String getId()               { return id; }
    public void setId(String id)        { this.id = id; }
    public String getNombre()           { return nombre; }
    public void setNombre(String n)     { this.nombre = n; }
    public String getCodigoAcceso()     { return codigoAcceso; }
    public void setCodigoAcceso(String c) { this.codigoAcceso = c; }
    public String getFechaCreacion()    { return fechaCreacion; }
    public void setFechaCreacion(String f) { this.fechaCreacion = f; }
}

