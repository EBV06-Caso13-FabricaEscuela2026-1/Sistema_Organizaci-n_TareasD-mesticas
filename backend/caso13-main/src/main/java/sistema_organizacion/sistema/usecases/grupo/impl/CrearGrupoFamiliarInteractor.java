package sistema_organizacion.sistema.usecases.grupo.impl;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoCommand;

public class CrearGrupoFamiliarInteractor implements CrearGrupoFamiliarUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final GrupoFamiliarOutputPort grupoOutputPort;

    public CrearGrupoFamiliarInteractor(UsuarioOutputPort usuarioOutputPort,
                                        GrupoFamiliarOutputPort grupoOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.grupoOutputPort = grupoOutputPort;
    }

    @Override
    public GrupoFamiliar ejecutar(CrearGrupoCommand command) {

        // CA-01-A y CA-01-B HU-02: verificar que es JefeDeHogar
        Usuario usuario = usuarioOutputPort.buscarPorId(command.getJefeId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        JefeDeHogar jefe = (JefeDeHogar) usuario;

        // La validación del nombre ocurre dentro de la entidad
        // CA-02-A, CA-02-B, CA-02-C HU-02
        GrupoFamiliar grupo = jefe.crearGrupo(command.getNombre());

        // CA-03-A y CA-04-A HU-02: guardar y devolver con código
        return grupoOutputPort.guardar(grupo);
    }
}