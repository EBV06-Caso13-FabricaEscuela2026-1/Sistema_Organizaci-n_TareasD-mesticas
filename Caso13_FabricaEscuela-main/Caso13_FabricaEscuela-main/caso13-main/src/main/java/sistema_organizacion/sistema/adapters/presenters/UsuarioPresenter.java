package sistema_organizacion.sistema.adapters.presenters;

import org.springframework.stereotype.Component;
import sistema_organizacion.sistema.adapters.dto.response.SesionResponse;
import sistema_organizacion.sistema.adapters.dto.response.UsuarioResponse;
import sistema_organizacion.sistema.entities.GrupoFamiliar;

import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.usecases.usuario.SesionResult;

@Component
public class UsuarioPresenter {

    public UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombreCompleto(usuario.getNombreCompleto());
        response.setCorreo(usuario.getCorreo());
        response.setUsername(usuario.getUsername());
        response.setRol(usuario.getRol().name());
        return response;
    }

    public SesionResponse toSesionResponse(SesionResult resultado) {
        SesionResponse response = new SesionResponse();
        response.setId(resultado.getUsuario().getId());
        response.setNombreCompleto(resultado.getUsuario().getNombreCompleto());
        response.setRol(resultado.getRol().name());
        response.setRedireccion(resultado.getRedireccion());

        GrupoFamiliar grupo = resultado.getGrupo();
        response.setGrupo(grupo != null
            ? grupo.getNombre()
            : "No pertenece a ningún grupo");

        if ("ADMIN".equals(resultado.getRol().name()) && grupo != null) {
            response.setCodigoAcceso(grupo.getCodigoAcceso());
        }

        return response;
    }
}
