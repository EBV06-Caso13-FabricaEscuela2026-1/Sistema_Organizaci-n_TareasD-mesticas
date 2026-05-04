package sistema_organizacion.sistema.usecases.usuario;

import sistema_organizacion.sistema.entities.Usuario;

public interface RegistrarUsuarioUseCase {
    Usuario ejecutar(RegistrarUsuarioCommand command);
}