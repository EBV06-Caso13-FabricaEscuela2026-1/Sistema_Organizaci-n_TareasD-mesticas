package sistema_organizacion.sistema.usecases.grupo.impl;

import java.util.UUID;

import sistema_organizacion.sistema.entities.*;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoEncontradoException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.*;

public class CrearGrupoInteractor implements CrearGrupoFamiliarUseCase {

    private final GrupoFamiliarOutputPort grupoOutputPort;
    private final UsuarioOutputPort usuarioOutputPort;

    public CrearGrupoInteractor(
        GrupoFamiliarOutputPort grupoOutputPort,
        UsuarioOutputPort usuarioOutputPort
    ) {
        this.grupoOutputPort = grupoOutputPort;
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public GrupoFamiliar ejecutar(CrearGrupoCommand command) {

        Usuario usuario = usuarioOutputPort.buscarPorId(command.getJefeId())
            .orElseThrow(() ->
                new UsuarioNoEncontradoException(command.getJefeId().toString()));

        
        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        
        if (usuario.getGrupo() != null) {
            throw new RuntimeException("El usuario ya pertenece a un grupo");
        }

        // Crear grupo con código
        String codigo = UUID.randomUUID().toString().substring(0, 6);

        GrupoFamiliar grupo = new GrupoFamiliar(
            command.getNombre(),
            codigo
        );

        GrupoFamiliar grupoGuardado = grupoOutputPort.guardar(grupo);

        
        usuario.setGrupo(grupoGuardado);
        usuarioOutputPort.guardar(usuario);

        return grupoGuardado;
    }
}