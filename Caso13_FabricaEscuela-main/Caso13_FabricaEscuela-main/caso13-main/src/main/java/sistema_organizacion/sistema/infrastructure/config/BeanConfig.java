package sistema_organizacion.sistema.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sistema_organizacion.sistema.ports.outs.DetalleTareaOutputPort;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.impl.CrearGrupoInteractor;
import sistema_organizacion.sistema.usecases.grupo.impl.IngresarGrupoInteractor;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.VerDetalleTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.impl.CrearTareaInteractor;
import sistema_organizacion.sistema.usecases.tarea.impl.ModificarTareaInteractor;
import sistema_organizacion.sistema.usecases.tarea.impl.VerDetalleTareaInteractor;
import sistema_organizacion.sistema.usecases.usuario.IniciarSesionUseCase;
import sistema_organizacion.sistema.usecases.usuario.RegistrarUsuarioUseCase;
import sistema_organizacion.sistema.usecases.usuario.impl.CrearDetalleTareaInteractor;
import sistema_organizacion.sistema.usecases.usuario.impl.IniciarSesionInteractor;
import sistema_organizacion.sistema.usecases.usuario.impl.RegistrarUsuarioInteractor;

@Configuration
public class BeanConfig {

    // USUARIOS
    @Bean
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(
            UsuarioOutputPort usuarioOutputPort) {
        return new RegistrarUsuarioInteractor(usuarioOutputPort);
    }

    @Bean
    public IniciarSesionUseCase iniciarSesionUseCase(
            UsuarioOutputPort usuarioOutputPort) {
        return new IniciarSesionInteractor(usuarioOutputPort);
    }

    // GRUPOS
    @Bean
    public CrearGrupoFamiliarUseCase crearGrupoFamiliarUseCase(
            UsuarioOutputPort usuarioOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort) {
        return new CrearGrupoInteractor(grupoOutputPort, usuarioOutputPort);
    }

    @Bean
    public IngresarGrupoFamiliarUseCase ingresarGrupoFamiliarUseCase(
            UsuarioOutputPort usuarioOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort) {
        return new IngresarGrupoInteractor(grupoOutputPort, usuarioOutputPort);
    }

    // TAREAS
    @Bean
    public CrearTareaUseCase crearTareaUseCase(
            UsuarioOutputPort usuarioOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort,
            TareaOutputPort tareaOutputPort) {
        return new CrearTareaInteractor(
            usuarioOutputPort, grupoOutputPort, tareaOutputPort);
    }

    @Bean
    public VerDetalleTareaUseCase verDetalleTareaUseCase(
            TareaOutputPort tareaOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort) {
        return new VerDetalleTareaInteractor(
            tareaOutputPort, grupoOutputPort);
    }

    @Bean
    public ModificarTareaUseCase modificarTareaUseCase(
            UsuarioOutputPort usuarioOutputPort,
            TareaOutputPort tareaOutputPort) {
        return new ModificarTareaInteractor(
            usuarioOutputPort, tareaOutputPort);
    }

    @Bean
    public CrearDetalleTareaInteractor crearDetalleTareaInteractor(
            DetalleTareaOutputPort detalleTareaOutputPort,
            TareaOutputPort tareaOutputPort) {
        return new CrearDetalleTareaInteractor(
            detalleTareaOutputPort, tareaOutputPort);
    }
}
