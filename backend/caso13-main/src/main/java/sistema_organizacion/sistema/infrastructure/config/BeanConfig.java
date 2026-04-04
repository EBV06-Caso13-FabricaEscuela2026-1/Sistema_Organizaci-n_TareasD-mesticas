package sistema_organizacion.sistema.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.impl.CrearGrupoFamiliarInteractor;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.VerDetalleTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.impl.CrearTareaInteractor;
import sistema_organizacion.sistema.usecases.tarea.impl.VerDetalleTareaInteractor;

@Configuration
public class BeanConfig {

    @Bean
    public CrearGrupoFamiliarUseCase crearGrupoFamiliarUseCase(
            UsuarioOutputPort usuarioOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort) {
        return new CrearGrupoFamiliarInteractor(usuarioOutputPort, grupoOutputPort);
    }

    @Bean
    public CrearTareaUseCase crearTareaUseCase(
            UsuarioOutputPort usuarioOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort,
            TareaOutputPort tareaOutputPort) {
        return new CrearTareaInteractor(
            usuarioOutputPort, grupoOutputPort, tareaOutputPort
        );
    }

    @Bean
    public VerDetalleTareaUseCase verDetalleTareaUseCase(
            TareaOutputPort tareaOutputPort,
            GrupoFamiliarOutputPort grupoOutputPort) {
        return new VerDetalleTareaInteractor(tareaOutputPort, grupoOutputPort);
    }
}