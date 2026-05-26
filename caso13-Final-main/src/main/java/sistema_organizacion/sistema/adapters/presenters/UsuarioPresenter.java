package sistema_organizacion.sistema.adapters.presenters;

import org.springframework.stereotype.Component;
import sistema_organizacion.sistema.adapters.dto.response.SesionResponse;
import sistema_organizacion.sistema.adapters.dto.response.UsuarioResponse;
import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.MiembroHogar;
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

        if (usuario instanceof JefeDeHogar jefe) {
            response.setEstado(jefe.getEstadoMiembro().name());
        } else if (usuario instanceof MiembroHogar miembro) {
            response.setEstado(miembro.getEstadoMiembro().name());
        }

        GrupoFamiliar grupo = usuario.getGrupo();
        if (grupo != null) {
            response.setGrupoId(grupo.getId());
            response.setGrupoNombre(grupo.getNombre());
        } else {
            response.setGrupoNombre("No pertenece a ningún grupo");
        }

        return response;
    }

    public SesionResponse toSesionResponse(SesionResult resultado) {
        SesionResponse response = new SesionResponse();
        response.setId(resultado.getUsuario().getId());
        response.setNombreCompleto(resultado.getUsuario().getNombreCompleto());
        response.setRol(resultado.getRol().name());
        response.setRedireccion(resultado.getRedireccion());

        GrupoFamiliar grupo = resultado.getGrupo();
        if (grupo != null) {
            response.setGrupoId(grupo.getId());
            response.setGrupo(grupo.getNombre());
        } else {
            response.setGrupo("No pertenece a ningún grupo");
        }

        if ("ADMIN".equals(resultado.getRol().name()) && grupo != null) {
            response.setCodigoAcceso(grupo.getCodigoAcceso());
        }

        return response;
    }
}
