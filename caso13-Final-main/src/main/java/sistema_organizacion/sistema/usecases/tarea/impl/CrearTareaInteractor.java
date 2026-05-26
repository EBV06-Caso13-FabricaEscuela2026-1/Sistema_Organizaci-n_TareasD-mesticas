package sistema_organizacion.sistema.usecases.tarea.impl;

import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;
import sistema_organizacion.sistema.entities.exception.NombreTareaDuplicadoException;
import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoEncontradoException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;

public class CrearTareaInteractor implements CrearTareaUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final GrupoFamiliarOutputPort grupoOutputPort;
    private final TareaOutputPort tareaOutputPort;

    public CrearTareaInteractor(UsuarioOutputPort usuarioOutputPort,
                                    GrupoFamiliarOutputPort grupoOutputPort,
                                    TareaOutputPort tareaOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.grupoOutputPort = grupoOutputPort;
        this.tareaOutputPort = tareaOutputPort;
    }

    @Override
    public Tarea ejecutar(CrearTareaCommand command) {

        // Verificar rol ADMIN
        Usuario usuario = usuarioOutputPort
            .buscarPorId(command.getJefeId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        // Verificar que el grupo existe
        GrupoFamiliar grupo = grupoOutputPort.buscarPorId(command.getGrupoId())
            .orElseThrow(() ->
                new GrupoFamiliarNoEncontradoException(command.getGrupoId().toString()));

        // Verificar que el jefe pertenece al grupo donde crea la tarea
        if (usuario.getGrupo() == null
                || !usuario.getGrupo().getId().equals(command.getGrupoId())) {
            throw new AccesoDenegadoException();
        }

        // Nombre único en el grupo
        boolean nombreDuplicado = tareaOutputPort
            .findByGrupoId(command.getGrupoId())
            .stream()
            .anyMatch(t -> t.getTitulo().equalsIgnoreCase(command.getTitulo()));

        if (nombreDuplicado) {
            throw new NombreTareaDuplicadoException(command.getTitulo());
        }

        Estado estadoPendiente = tareaOutputPort.buscarEstadoPorNombre(EstadoTarea.PENDIENTE);

        Tarea tarea = new Tarea(
            command.getTitulo(),
            command.getDescripcion(),
            command.getFechaLimite(),
            grupo,
            estadoPendiente
        );

        // HU-16 CA-01-C/CA-02: asignación opcional de miembro (incluye JefeDeHogar)
        if (command.getUsuarioAsignadoId() != null) {
            Usuario miembro = usuarioOutputPort
                .buscarPorId(command.getUsuarioAsignadoId())
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                    command.getUsuarioAsignadoId().toString()));

            if (miembro.getGrupo() == null
                    || !miembro.getGrupo().getId().equals(command.getGrupoId())) {
                throw new TareaInvalidaException(
                    "El miembro no pertenece al grupo familiar de la tarea");
            }

            tarea.asignarResponsable(miembro, miembro.getNombreCompleto());
        }

        return tareaOutputPort.guardar(tarea);
    }
}
