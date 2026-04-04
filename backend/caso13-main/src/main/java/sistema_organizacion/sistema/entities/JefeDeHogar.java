package sistema_organizacion.sistema.entities;

import java.time.LocalDate;

import sistema_organizacion.sistema.entities.exception.NombreGrupoInvalidoException;

public class JefeDeHogar extends Usuario {

    public JefeDeHogar(String id, String nombre, String apellido,
                       String correo, String username, String contrasena) {
        super(id, nombre, apellido, correo, username, contrasena);
    }

    // Regla de negocio: el jefe es quien puede crear el grupo
    // CA-01-A HU-02: solo administrador puede crear grupos
    public GrupoFamiliar crearGrupo(String nombre) {
        validarNombreGrupo(nombre);
        String codigo = generarCodigoUnico();
        return new GrupoFamiliar(null, nombre, codigo, this.getId());
    }

    // CA-02-A HU-02: entre 6 y 25 caracteres
    // CA-02-B HU-02: solo letras, números, espacios, tildes, ñ
    // CA-02-C HU-02: sin caracteres especiales
    private void validarNombreGrupo(String nombre) {
        if (nombre == null || nombre.trim().length() < 6 || nombre.trim().length() > 25) {
            throw new NombreGrupoInvalidoException(
                "El nombre debe tener entre 6 y 25 caracteres"
            );
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9 ]+")) {
            throw new NombreGrupoInvalidoException(
                "El nombre solo permite letras, números, espacios, tildes y ñ"
            );
        }
    }

    private String generarCodigoUnico() {
        return "GRP-" + (int)(Math.random() * 900000 + 100000);
    }

    // Regla: solo el jefe puede crear tareas — HU-11
    public Tarea crearTarea(String titulo, String descripcion,
                             LocalDate fechaLimite, String grupoId) {
        return new Tarea(null, titulo, descripcion, fechaLimite, grupoId);
    }
}