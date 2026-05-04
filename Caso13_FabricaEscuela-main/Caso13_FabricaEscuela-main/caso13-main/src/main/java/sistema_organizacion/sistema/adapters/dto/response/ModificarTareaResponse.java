package sistema_organizacion.sistema.adapters.dto.response;

public class ModificarTareaResponse {
    private String mensaje;
    private TareaResponse tarea;

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public TareaResponse getTarea() { return tarea; }
    public void setTarea(TareaResponse tarea) { this.tarea = tarea; }
}
