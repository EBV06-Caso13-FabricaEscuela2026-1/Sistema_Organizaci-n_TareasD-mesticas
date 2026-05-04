package sistema_organizacion.sistema.adapters.dto.request;

import java.time.LocalDate;

public class ModificarTareaRequest {
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fechaLimite;

    public String getTitulo()                        { return titulo; }
    public void setTitulo(String titulo)             { this.titulo = titulo; }

    public String getDescripcion()                   { return descripcion; }
    public void setDescripcion(String descripcion)   { this.descripcion = descripcion; }

    public String getEstado()                        { return estado; }
    public void setEstado(String estado)             { this.estado = estado; }

    public LocalDate getFechaLimite()                { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }
}
