package sistema_organizacion.sistema.usecases.usuario;

public interface IniciarSesionUseCase {
    SesionResult ejecutar(IniciarSesionCommand command);
}
