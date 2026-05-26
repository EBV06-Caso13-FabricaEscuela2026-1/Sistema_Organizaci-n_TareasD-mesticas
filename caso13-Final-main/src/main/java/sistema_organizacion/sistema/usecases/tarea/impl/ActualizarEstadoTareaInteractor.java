package sistema_organizacion.sistema.usecases.tarea.impl;

import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.MiembroHogar;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;
import sistema_organizacion.sistema.entities.exception.TareaNoEncontradaException;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.tarea.ActualizarEstadoTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.ActualizarEstadoTareaUseCase;

public class ActualizarEstadoTareaInteractor implements ActualizarEstadoTareaUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final TareaOutputPort tareaOutputPort;

    public ActualizarEstadoTareaInteractor(UsuarioOutputPort usuarioOutputPort,
                                           TareaOutputPort tareaOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.tareaOutputPort = tareaOutputPort;
    }

    @Override
    public Tarea ejecutar(ActualizarEstadoTareaCommand command) {
        Usuario usuario = usuarioOutputPort
            .buscarPorId(command.getMiembroId())
            .orElseThrow(AccesoDenegadoException::new);

        Tarea tarea = tareaOutputPort
            .buscarPorId(command.getTareaId())
            .orElseThrow(() -> new TareaNoEncontradaException(command.getTareaId()));

        boolean esAdmin = usuario instanceof JefeDeHogar;
        boolean esMiembroAsignado = usuario instanceof MiembroHogar
                && tarea.getMiembroAsignadoId() != null
                && tarea.getMiembroAsignadoId().equals(command.getMiembroId());

        if (!esAdmin && !esMiembroAsignado) {
            throw new AccesoDenegadoException();
        }

        EstadoTarea nuevoEstado;
        try {
            nuevoEstado = EstadoTarea.valueOf(command.getNuevoEstado());
        } catch (IllegalArgumentException e) {
            throw new TareaInvalidaException(
                "Estado inválido. Los valores permitidos son: PENDIENTE, EN_PROCESO, TERMINADA"
            );
        }

        // Miembros no pueden regresar el estado a PENDIENTE
        if (!esAdmin && nuevoEstado == EstadoTarea.PENDIENTE) {
            throw new TareaInvalidaException(
                "No se puede establecer el estado a PENDIENTE"
            );
        }

        Estado estadoEntidad = tareaOutputPort.buscarEstadoPorNombre(nuevoEstado);
        tarea.actualizarEstado(estadoEntidad);

        return tareaOutputPort.guardar(tarea);
    }
}
