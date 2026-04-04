package sistema_organizacion.sistema.usecases.grupo;
import sistema_organizacion.sistema.entities.GrupoFamiliar;

public interface CrearGrupoFamiliarUseCase {
    GrupoFamiliar ejecutar(CrearGrupoCommand command);
}