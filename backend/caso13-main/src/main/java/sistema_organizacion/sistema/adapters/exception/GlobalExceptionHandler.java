package sistema_organizacion.sistema.adapters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;
import sistema_organizacion.sistema.entities.exception.NombreGrupoInvalidoException;
import sistema_organizacion.sistema.entities.exception.NombreTareaDuplicadoException;
import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;
import sistema_organizacion.sistema.entities.exception.TareaNoEncontradaException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // HU-02 validaciones nombre grupo
    @ExceptionHandler(NombreGrupoInvalidoException.class)
    public ResponseEntity<Map<String, String>> handleNombreGrupoInvalido(
            NombreGrupoInvalidoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    // HU-11 validaciones tarea
    @ExceptionHandler(TareaInvalidaException.class)
    public ResponseEntity<Map<String, String>> handleTareaInvalida(
            TareaInvalidaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
    // HU-11 nombre duplicado
    @ExceptionHandler(NombreTareaDuplicadoException.class)
    public ResponseEntity<Map<String, String>> handleNombreDuplicado(
            NombreTareaDuplicadoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
    // HU-12 tarea no encontrada
    @ExceptionHandler(TareaNoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleTareaNoEncontrada(
            TareaNoEncontradaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    // CA-01-B HU-02 y HU-11: no es jefe
    @ExceptionHandler(AccesoDenegadoException.class)
    public ResponseEntity<Map<String, String>> handleAccesoDenegado(
            AccesoDenegadoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
    
        @ExceptionHandler(GrupoFamiliarNoEncontradoException.class)
        public ResponseEntity<Map<String, String>> handleGrupoNoEncontrado(
                GrupoFamiliarNoEncontradoException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
