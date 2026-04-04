package sistema_organizacion.sistema.adapters.dto.request;

import java.time.LocalDate;

public class CrearTareaRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private String grupoId;

    public String getTitulo()             { return titulo; }
    public void setTitulo(String t)       { this.titulo = t; }
    public String getDescripcion()        { return descripcion; }
    public void setDescripcion(String d)  { this.descripcion = d; }
    public LocalDate getFechaLimite()     { return fechaLimite; }
    public void setFechaLimite(LocalDate f) { this.fechaLimite = f; }
    public String getGrupoId()            { return grupoId; }
    public void setGrupoId(String g)      { this.grupoId = g; }
}