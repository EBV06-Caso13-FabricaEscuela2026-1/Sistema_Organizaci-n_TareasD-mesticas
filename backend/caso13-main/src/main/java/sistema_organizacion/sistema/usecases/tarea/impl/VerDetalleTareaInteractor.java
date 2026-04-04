package sistema_organizacion.sistema.usecases.tarea.impl;

import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.exception.GrupoFamiliarNoEncontradoException;  
import sistema_organizacion.sistema.entities.exception.TareaNoEncontradaException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.usecases.tarea.VerDetalleTareaUseCase;

public class VerDetalleTareaInteractor implements VerDetalleTareaUseCase {

    private final TareaOutputPort tareaOutputPort;
    private final GrupoFamiliarOutputPort grupoOutputPort;

    public VerDetalleTareaInteractor(TareaOutputPort tareaOutputPort,
                                      GrupoFamiliarOutputPort grupoOutputPort) {
        this.tareaOutputPort = tareaOutputPort;
        this.grupoOutputPort = grupoOutputPort;
    }

    @Override
    public Tarea ejecutar(String tareaId, String grupoId) {

        // Verificar que el grupo existe
        grupoOutputPort.buscarPorId(grupoId)
            .orElseThrow(() -> new GrupoFamiliarNoEncontradoException(grupoId));

        // CA-01-A HU-12: retorna la tarea con todos sus datos
        // CA-05-A HU-12: lanza excepción si no existe
        return tareaOutputPort.buscarPorId(tareaId)
            .orElseThrow(() -> new TareaNoEncontradaException(tareaId));
    }
}