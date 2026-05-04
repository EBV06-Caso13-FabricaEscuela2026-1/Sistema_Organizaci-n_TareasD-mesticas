package sistema_organizacion.sistema.usecases.usuario.impl;

import sistema_organizacion.sistema.entities.DetalleTarea;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.ports.outs.DetalleTareaOutputPort;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.usecases.grupo.CrearDetalleTareaCommand;

public class CrearDetalleTareaInteractor {

    private final DetalleTareaOutputPort detalleTareaOutputPort;
    private final TareaOutputPort tareaOutputPort;

    public CrearDetalleTareaInteractor(DetalleTareaOutputPort detalleTareaOutputPort,
                                        TareaOutputPort tareaOutputPort) {
        this.detalleTareaOutputPort = detalleTareaOutputPort;
        this.tareaOutputPort = tareaOutputPort;
    }

    public DetalleTarea ejecutar(CrearDetalleTareaCommand command) {
        Tarea tarea = tareaOutputPort.buscarPorId(command.getTareaId())
            .orElseThrow(AccesoDenegadoException::new);

        DetalleTarea detalle = new DetalleTarea(
            tarea,
            command.getDescripcion(),
            command.getObservacion()
        );

        return detalleTareaOutputPort.guardar(detalle);
    }
}
