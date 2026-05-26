package sistema_organizacion.sistema.adapters.dto.request;

public class ActualizarEstadoRequest {
    private String nuevoEstado;

    public String getNuevoEstado()              { return nuevoEstado; }
    public void setNuevoEstado(String estado)   { this.nuevoEstado = estado; }
}
