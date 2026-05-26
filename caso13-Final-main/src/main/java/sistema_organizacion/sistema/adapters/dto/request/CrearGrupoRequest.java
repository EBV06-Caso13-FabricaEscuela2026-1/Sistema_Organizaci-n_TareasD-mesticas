package sistema_organizacion.sistema.adapters.dto.request;

public class CrearGrupoRequest {
    private String nombre;
    private Long jefeId;
    
    public String getNombre()         { return nombre; }
    public void setNombre(String n)   { this.nombre = n; }
    public Long getJefeId()           { return jefeId; }
    public void setJefeId(Long j)     { this.jefeId = j; }
}