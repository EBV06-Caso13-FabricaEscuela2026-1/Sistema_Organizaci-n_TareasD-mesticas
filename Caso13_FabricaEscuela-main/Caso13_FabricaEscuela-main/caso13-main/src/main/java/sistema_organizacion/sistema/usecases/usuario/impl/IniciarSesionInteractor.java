package sistema_organizacion.sistema.usecases.usuario.impl;

import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.CamposObligatoriosException;
import sistema_organizacion.sistema.entities.exception.CredencialesInvalidasException;
import sistema_organizacion.sistema.entities.exception.UsuarioNoRegistradoException;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.usuario.IniciarSesionCommand;
import sistema_organizacion.sistema.usecases.usuario.IniciarSesionUseCase;
import sistema_organizacion.sistema.usecases.usuario.SesionResult;

public class IniciarSesionInteractor implements IniciarSesionUseCase {

    private final UsuarioOutputPort usuarioOutputPort;

    public IniciarSesionInteractor(UsuarioOutputPort usuarioOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public SesionResult ejecutar(IniciarSesionCommand command) {

        if (command.getCorreo() == null || command.getCorreo().isBlank()
                || command.getContrasena() == null
                || command.getContrasena().isBlank()) {
            throw new CamposObligatoriosException();
        }

        Usuario usuario = usuarioOutputPort
            .buscarPorCorreo(command.getCorreo())
            .orElseThrow(UsuarioNoRegistradoException::new);

        if (!usuario.getContrasena().equals(command.getContrasena())) {
            throw new CredencialesInvalidasException("Credenciales incorrectas");
        }

        return new SesionResult(usuario, usuario.getGrupo());
    }
}
