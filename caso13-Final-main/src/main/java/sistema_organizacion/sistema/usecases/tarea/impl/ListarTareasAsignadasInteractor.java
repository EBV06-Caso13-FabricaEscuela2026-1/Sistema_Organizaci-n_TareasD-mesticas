package sistema_organizacion.sistema.usecases.tarea.impl;

import java.util.List;

import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.tarea.ListarTareasAsignadasUseCase;

public class ListarTareasAsignadasInteractor implements ListarTareasAsignadasUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final TareaOutputPort tareaOutputPort;

    public ListarTareasAsignadasInteractor(UsuarioOutputPort usuarioOutputPort,
                                           TareaOutputPort tareaOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.tareaOutputPort = tareaOutputPort;
    }

    @Override
    public List<Tarea> ejecutar(Long miembroId) {
        Usuario usuario = usuarioOutputPort
            .buscarPorId(miembroId)
            .orElseThrow(AccesoDenegadoException::new);

        return tareaOutputPort.findByUsuarioId(usuario.getId());
    }
}
