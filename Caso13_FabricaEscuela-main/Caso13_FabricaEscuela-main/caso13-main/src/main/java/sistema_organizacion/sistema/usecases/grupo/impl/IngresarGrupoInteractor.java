package sistema_organizacion.sistema.usecases.grupo.impl;

import sistema_organizacion.sistema.entities.*;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoEncontradoException;
import sistema_organizacion.sistema.ports.outs.*;
import sistema_organizacion.sistema.usecases.grupo.*;

public class IngresarGrupoInteractor implements IngresarGrupoFamiliarUseCase {

    private final GrupoFamiliarOutputPort grupoOutputPort;
    private final UsuarioOutputPort usuarioOutputPort;

    public IngresarGrupoInteractor(
        GrupoFamiliarOutputPort grupoOutputPort,
        UsuarioOutputPort usuarioOutputPort
    ) {
        this.grupoOutputPort = grupoOutputPort;
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public GrupoFamiliar ejecutar(IngresarGrupoCommand command) {

        Usuario usuario = usuarioOutputPort.buscarPorId(command.getUsuarioId())
            .orElseThrow(() ->
                new UsuarioNoEncontradoException(command.getUsuarioId().toString()));

        if (!(usuario instanceof MiembroHogar miembro)) {
            throw new AccesoDenegadoException();
        }

        GrupoFamiliar grupo = grupoOutputPort
            .buscarPorCodigoAcceso(command.getCodigoAcceso())
            .orElseThrow(() ->
                new GrupoFamiliarNoEncontradoException(command.getCodigoAcceso()));

        miembro.asignarGrupo(grupo);

        usuarioOutputPort.guardar(miembro);

        return grupo;
    }
}
