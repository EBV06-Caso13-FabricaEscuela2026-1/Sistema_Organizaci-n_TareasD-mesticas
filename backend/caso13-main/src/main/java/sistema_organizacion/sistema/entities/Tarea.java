package sistema_organizacion.sistema.entities;

import java.time.LocalDate;
import java.util.Arrays;

import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;  

public class Tarea {
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private EstadoTarea estado;
    private String grupoId;
    private String miembroAsignadoId;
    private String nombreMiembroAsignado;

    public Tarea(String id, String titulo, String descripcion,
                 LocalDate fechaLimite, String grupoId) {
        validarTitulo(titulo);
        validarDescripcion(descripcion);
        validarFechaLimite(fechaLimite);
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.grupoId = grupoId;
        // CA-04-A HU-11: estado por defecto PENDIENTE
        this.estado = EstadoTarea.PENDIENTE;
    }

    // CA-01-D HU-11: entre 3 y 60 caracteres
    // CA-01-E HU-11: solo letras, números, espacios, tildes, ñ
    // CA-01-F HU-11: sin caracteres especiales
    private void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 3 || titulo.trim().length() > 60) {
            throw new TareaInvalidaException(
                "El nombre debe tener entre 3 y 60 caracteres"
            );
        }
        if (!titulo.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9 ]+")) {
            throw new TareaInvalidaException(
                "El nombre solo permite letras, números, espacios, tildes y ñ"
            );
        }
    }

    // CA-02-A HU-11: hasta 100 palabras
    // CA-02-C HU-11: puede estar vacía
    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) return;
        long palabras = Arrays.stream(descripcion.trim().split("\\s+")).count();
        if (palabras > 100) {
            throw new TareaInvalidaException(
                "La descripción no debe superar las 100 palabras"
            );
        }
    }

    // CA-03-A HU-11: fecha posterior a hoy
    // CA-03-B HU-11: no puede ser pasada
    private void validarFechaLimite(LocalDate fecha) {
        if (fecha == null || !fecha.isAfter(LocalDate.now())) {
            throw new TareaInvalidaException(
                "La fecha límite no puede estar en el pasado"
            );
        }
    }

    public void asignarMiembro(String miembroId, String nombreMiembro) {
        this.miembroAsignadoId = miembroId;
        this.nombreMiembroAsignado = nombreMiembro;
    }

    // CA-04 HU-12: el estado puede cambiar
    public void actualizarEstado(EstadoTarea nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getId()           { return id; }
    public String getTitulo()       { return titulo; }
    public String getDescripcion()  { return descripcion; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public EstadoTarea getEstado()  { return estado; }
    public String getGrupoId()      { return grupoId; }
    public String getMiembroAsignadoId()     { return miembroAsignadoId; }
    public String getNombreMiembroAsignado() { return nombreMiembroAsignado; }

    public void setId(String id)    { this.id = id; }
}