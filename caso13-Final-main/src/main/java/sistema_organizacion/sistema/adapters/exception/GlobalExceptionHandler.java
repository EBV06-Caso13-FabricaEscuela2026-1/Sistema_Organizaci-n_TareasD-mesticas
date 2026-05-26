package sistema_organizacion.sistema.adapters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.AdministradorYaExisteException;
import sistema_organizacion.sistema.entities.exception.CamposObligatoriosException;
import sistema_organizacion.sistema.entities.exception.CorreoInvalidoException;
import sistema_organizacion.sistema.entities.exception.ContrasenaInvalidaException;
import sistema_organizacion.sistema.entities.exception.CredencialesInvalidasException;
import sistema_organizacion.sistema.entities.exception.CodigoAccesoInvalidoException;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;
import sistema_organizacion.sistema.entities.exception.NombreGrupoInvalidoException;
import sistema_organizacion.sistema.entities.exception.NombreTareaDuplicadoException;
import sistema_organizacion.sistema.entities.exception.RolNoSeleccionadoException;
import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;
import sistema_organizacion.sistema.entities.exception.TareaNoEncontradaException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoRegistradoException;
import java.time.format.DateTimeParseException;
import sistema_organizacion.sistema.entities.exception.MiembroYaEnGrupoException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoEncontradoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CorreoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCorreoInvalido(
            CorreoInvalidoException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ContrasenaInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleContrasenaInvalida(
            ContrasenaInvalidaException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(RolNoSeleccionadoException.class)
    public ResponseEntity<ErrorResponse> handleRolNoSeleccionado(
            RolNoSeleccionadoException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(AdministradorYaExisteException.class)
    public ResponseEntity<ErrorResponse> handleAdminYaExiste(
            AdministradorYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<ErrorResponse> handleCredencialesInvalidas(
            CredencialesInvalidasException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(UsuarioNoRegistradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNoRegistrado(
            UsuarioNoRegistradoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(CamposObligatoriosException.class)
    public ResponseEntity<ErrorResponse> handleCamposObligatorios(
            CamposObligatoriosException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MiembroYaEnGrupoException.class)
    public ResponseEntity<ErrorResponse> handleMiembroYaEnGrupo(
            MiembroYaEnGrupoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(CodigoAccesoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCodigoInvalido(
            CodigoAccesoInvalidoException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NombreGrupoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleNombreGrupoInvalido(
            NombreGrupoInvalidoException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(TareaInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleTareaInvalida(
            TareaInvalidaException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(TareaNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleTareaNoEncontrada(
            TareaNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(AccesoDenegadoException.class)
    public ResponseEntity<ErrorResponse> handleAccesoDenegado(
            AccesoDenegadoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NombreTareaDuplicadoException.class)
    public ResponseEntity<ErrorResponse> handleNombreDuplicado(
            NombreTareaDuplicadoException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(GrupoFamiliarNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleGrupoNoEncontrado(
            GrupoFamiliarNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNoEncontrado(
            UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleFechaInvalida(
            DateTimeParseException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(
                "Formato de fecha inválido. Use el formato yyyy-MM-dd (ej: 2025-12-31)"));
    }
}