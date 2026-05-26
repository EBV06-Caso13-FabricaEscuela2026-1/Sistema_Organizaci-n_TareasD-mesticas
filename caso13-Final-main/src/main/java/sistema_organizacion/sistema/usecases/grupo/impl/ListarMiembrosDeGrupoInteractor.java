package sistema_organizacion.sistema.usecases.grupo.impl;

import java.util.List;

import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.ListarMiembrosDeGrupoUseCase;

public class ListarMiembrosDeGrupoInteractor implements ListarMiembrosDeGrupoUseCase {

    private final GrupoFamiliarOutputPort grupoOutputPort;
    private final UsuarioOutputPort usuarioOutputPort;

    public ListarMiembrosDeGrupoInteractor(GrupoFamiliarOutputPort grupoOutputPort,
                                           UsuarioOutputPort usuarioOutputPort) {
        this.grupoOutputPort = grupoOutputPort;
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public List<Usuario> ejecutar(Long grupoId) {
        grupoOutputPort.buscarPorId(grupoId)
            .orElseThrow(() -> new GrupoFamiliarNoEncontradoException(grupoId.toString()));

        return usuarioOutputPort.listarMiembrosPorGrupo(grupoId);
    }
}
