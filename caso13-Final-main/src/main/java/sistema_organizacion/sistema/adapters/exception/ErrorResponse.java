package sistema_organizacion.sistema.adapters.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String mensaje;
    private final String timestamp;

    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getMensaje()   { return mensaje; }
    public String getTimestamp() { return timestamp; }
}