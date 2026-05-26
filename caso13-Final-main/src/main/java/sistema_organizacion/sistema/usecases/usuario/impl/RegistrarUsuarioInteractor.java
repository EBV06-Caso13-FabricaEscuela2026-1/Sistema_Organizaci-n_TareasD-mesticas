package sistema_organizacion.sistema.usecases.usuario.impl;

import sistema_organizacion.sistema.entities.*;
import sistema_organizacion.sistema.entities.exception.RolNoSeleccionadoException;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.usuario.*;

public class RegistrarUsuarioInteractor implements RegistrarUsuarioUseCase {

    private final UsuarioOutputPort usuarioOutputPort;

    public RegistrarUsuarioInteractor(UsuarioOutputPort usuarioOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public Usuario ejecutar(RegistrarUsuarioCommand command) {

        if (command.getRol() == null) {
            throw new RolNoSeleccionadoException();
        }

        Rol rol = usuarioOutputPort.buscarRolPorNombre(command.getRol());

        Usuario nuevo;

        if (command.getRol() == RolUsuario.ADMIN) {
            nuevo = new JefeDeHogar(
                command.getNombre(),
                command.getApellido(),
                command.getCorreo(),
                command.getUsername(),
                command.getContrasena(),
                rol
            );
        } else {
            nuevo = new MiembroHogar(
                command.getNombre(),
                command.getApellido(),
                command.getCorreo(),
                command.getUsername(),
                command.getContrasena(),
                rol
            );
        }

        return usuarioOutputPort.guardar(nuevo);
    }
}