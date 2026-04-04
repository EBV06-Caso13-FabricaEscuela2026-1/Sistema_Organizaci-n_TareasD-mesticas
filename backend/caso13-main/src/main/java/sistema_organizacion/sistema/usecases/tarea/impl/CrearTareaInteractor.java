package sistema_organizacion.sistema.usecases.tarea.impl;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;
import sistema_organizacion.sistema.entities.exception.NombreTareaDuplicadoException;
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

        // Verificar que es JefeDeHogar — HU-11
        Usuario usuario = usuarioOutputPort.buscarPorId(command.getJefeId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        // Verificar que el grupo existe
        GrupoFamiliar grupo = grupoOutputPort.buscarPorId(command.getGrupoId())
            .orElseThrow(() ->
                new GrupoFamiliarNoEncontradoException(command.getGrupoId()));

        // CA-01-A y CA-01-B HU-11: nombre único en el grupo
        boolean nombreDuplicado = tareaOutputPort
            .buscarPorGrupoId(command.getGrupoId())
            .stream()
            .anyMatch(t -> t.getTitulo().equalsIgnoreCase(command.getTitulo()));

        if (nombreDuplicado) {
            throw new NombreTareaDuplicadoException(command.getTitulo());
        }

        JefeDeHogar jefe = (JefeDeHogar) usuario;

        // Validaciones de negocio ocurren dentro de la entidad Tarea
        // CA-01-C/D/E/F, CA-02-A/B/C, CA-03-A/B, CA-04-A HU-11
        Tarea tarea = jefe.crearTarea(
            command.getTitulo(),
            command.getDescripcion(),
            command.getFechaLimite(),
            command.getGrupoId()
        );

        return tareaOutputPort.guardar(tarea);
    }
}