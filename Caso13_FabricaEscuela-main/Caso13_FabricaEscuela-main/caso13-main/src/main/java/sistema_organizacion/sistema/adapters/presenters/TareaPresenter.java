package sistema_organizacion.sistema.adapters.presenters;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.adapters.dto.response.TareaResponse;
import sistema_organizacion.sistema.entities.Tarea;

@Component
public class TareaPresenter {

    // CA-01-A HU-12: mapea todos los campos del detalle
    // CA-02-A HU-12: si no hay miembro asignado muestra mensaje
    // CA-04 HU-12: muestra estado actual
    public TareaResponse toResponse(Tarea tarea) {
        TareaResponse response = new TareaResponse();
        response.setId(tarea.getId());
        response.setTitulo(tarea.getTitulo());
        response.setDescripcion(tarea.getDescripcion());
        response.setFechaLimite(tarea.getFechaLimite().toString());
        response.setEstado(tarea.getEstado().name());
        response.setGrupoId(tarea.getGrupoId());

        // CA-03 HU-12: mensaje cuando no tiene miembro asignado
        if (tarea.getMiembroAsignadoId() == null) {
            response.setMiembroAsignado("Sin integrante asignado");
        } else {
            response.setMiembroAsignado(tarea.getNombreMiembroAsignado());
        }
        return response;
    }
}