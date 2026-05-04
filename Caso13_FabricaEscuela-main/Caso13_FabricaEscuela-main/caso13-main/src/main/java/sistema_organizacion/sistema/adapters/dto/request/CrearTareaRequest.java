package sistema_organizacion.sistema.adapters.dto.request;

import java.time.LocalDate;

public class CrearTareaRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private Long grupoId;

    public String getTitulo()             { return titulo; }
    public void setTitulo(String t)       { this.titulo = t; }
    public String getDescripcion()        { return descripcion; }
    public void setDescripcion(String d)  { this.descripcion = d; }
    public LocalDate getFechaLimite()     { return fechaLimite; }
    public void setFechaLimite(LocalDate f) { this.fechaLimite = f; }
    public Long getGrupoId()              { return grupoId; }
    public void setGrupoId(Long g)        { this.grupoId = g; }
}