package sistema_organizacion.sistema.adapters.presenters;
import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.adapters.dto.response.GrupoFamiliarResponse;
import sistema_organizacion.sistema.entities.GrupoFamiliar;

@Component
public class GrupoFamiliarPresenter {

    public GrupoFamiliarResponse toResponse(GrupoFamiliar grupo) {
        GrupoFamiliarResponse response = new GrupoFamiliarResponse();
        response.setId(grupo.getId());
        response.setNombre(grupo.getNombre());
        response.setCodigoAcceso(grupo.getCodigoAcceso());
        response.setFechaCreacion(grupo.getFechaCreacion().toString());
        return response;
    }
}