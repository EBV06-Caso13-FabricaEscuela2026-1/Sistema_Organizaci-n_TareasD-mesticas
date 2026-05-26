package sistema_organizacion.sistema.usecases.tarea.impl;

import java.time.LocalDate;

import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;
import sistema_organizacion.sistema.entities.exception.TareaNoEncontradaException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoEncontradoException;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaUseCase;

public class ModificarTareaInteractor implements ModificarTareaUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final TareaOutputPort tareaOutputPort;

    public ModificarTareaInteractor(UsuarioOutputPort usuarioOutputPort,
                                    TareaOutputPort tareaOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.tareaOutputPort = tareaOutputPort;
    }

    @Override
    public Tarea ejecutar(ModificarTareaCommand command) {

        Usuario usuario = usuarioOutputPort
            .buscarPorId(command.getJefeId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        Tarea tarea = tareaOutputPort
            .buscarPorId(command.getTareaId())
            .orElseThrow(() -> new TareaNoEncontradaException(command.getTareaId()));

        // Verificar que el jefe pertenece al grupo de la tarea
        if (usuario.getGrupo() == null
                || !usuario.getGrupo().getId().equals(tarea.getGrupoId())) {
            throw new AccesoDenegadoException();
        }

        String titulo = esValido(command.getNuevoTitulo())
            ? command.getNuevoTitulo()
            : tarea.getTitulo();

        String descripcion = esValido(command.getNuevaDescripcion())
            ? command.getNuevaDescripcion()
            : tarea.getDescripcion();

        LocalDate fechaLimite = command.getNuevaFechaLimite() != null
            ? command.getNuevaFechaLimite()
            : tarea.getFechaLimite();

        tarea.actualizar(titulo, descripcion, fechaLimite);

        // HU-16: gestión de asignación de miembro (incluye JefeDeHogar)
        if (command.isLimpiarAsignacion()) {
            // CA-04: eliminar asignación
            tarea.asignarResponsable(null, null);
        } else if (command.getUsuarioAsignadoId() != null) {
            // CA-03: asignar o cambiar miembro responsable
            Usuario miembro = usuarioOutputPort
                .buscarPorId(command.getUsuarioAsignadoId())
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                    command.getUsuarioAsignadoId().toString()));

            if (miembro.getGrupo() == null
                    || !miembro.getGrupo().getId().equals(tarea.getGrupoId())) {
                throw new TareaInvalidaException(
                    "El miembro no pertenece al grupo familiar de la tarea");
            }

            tarea.asignarResponsable(miembro, miembro.getNombreCompleto());
        }

        return tareaOutputPort.guardar(tarea);
    }

    private boolean esValido(String valor) {
        return valor != null && !valor.isBlank();
    }
}
