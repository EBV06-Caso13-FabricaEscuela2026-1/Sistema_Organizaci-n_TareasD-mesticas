package sistema_organizacion.sistema.usecases.grupo;

import sistema_organizacion.sistema.entities.GrupoFamiliar;

public interface IngresarGrupoFamiliarUseCase {
    GrupoFamiliar ejecutar(IngresarGrupoCommand command);
}