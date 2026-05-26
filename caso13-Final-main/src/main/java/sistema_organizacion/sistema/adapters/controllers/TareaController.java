package sistema_organizacion.sistema.adapters.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistema_organizacion.sistema.adapters.dto.request.ActualizarEstadoRequest;
import sistema_organizacion.sistema.adapters.dto.request.CrearTareaRequest;
import sistema_organizacion.sistema.adapters.dto.request.ModificarTareaRequest;
import sistema_organizacion.sistema.adapters.dto.response.MensajeResponse;
import sistema_organizacion.sistema.adapters.dto.response.ModificarTareaResponse;
import sistema_organizacion.sistema.adapters.dto.response.TareaResponse;
import sistema_organizacion.sistema.adapters.presenters.TareaPresenter;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.usecases.tarea.ActualizarEstadoTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.ActualizarEstadoTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.EliminarTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.EliminarTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.ListarTareasAsignadasUseCase;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.VerDetalleTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.ListarTareasPorGrupoUseCase;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final CrearTareaUseCase crearTareaUseCase;
    private final ModificarTareaUseCase modificarTareaUseCase;
    private final VerDetalleTareaUseCase verDetalleTareaUseCase;
    private final ListarTareasPorGrupoUseCase listarTareasPorGrupoUseCase;
    private final ActualizarEstadoTareaUseCase actualizarEstadoTareaUseCase;
    private final ListarTareasAsignadasUseCase listarTareasAsignadasUseCase;
    private final EliminarTareaUseCase eliminarTareaUseCase;
    private final TareaPresenter presenter;

    public TareaController(
            CrearTareaUseCase crearTareaUseCase,
            ModificarTareaUseCase modificarTareaUseCase,
            VerDetalleTareaUseCase verDetalleTareaUseCase,
            ListarTareasPorGrupoUseCase listarTareasPorGrupoUseCase,
            ActualizarEstadoTareaUseCase actualizarEstadoTareaUseCase,
            ListarTareasAsignadasUseCase listarTareasAsignadasUseCase,
            EliminarTareaUseCase eliminarTareaUseCase,
            TareaPresenter presenter) {
        this.crearTareaUseCase = crearTareaUseCase;
        this.modificarTareaUseCase = modificarTareaUseCase;
        this.verDetalleTareaUseCase = verDetalleTareaUseCase;
        this.listarTareasPorGrupoUseCase = listarTareasPorGrupoUseCase;
        this.actualizarEstadoTareaUseCase = actualizarEstadoTareaUseCase;
        this.listarTareasAsignadasUseCase = listarTareasAsignadasUseCase;
        this.eliminarTareaUseCase = eliminarTareaUseCase;
        this.presenter = presenter;
    }

    // HU-11: crear tarea (con asignación opcional HU-16)
    @Transactional
    @PostMapping
    public ResponseEntity<TareaResponse> crear(
            @RequestBody CrearTareaRequest request,
            @RequestHeader("X-Usuario-Id") Long jefeId) {

        CrearTareaCommand command = new CrearTareaCommand(
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaLimite(),
            request.getGrupoId(),
            jefeId,
            request.getUsuarioAsignadoId()
        );
        Tarea tarea = crearTareaUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(presenter.toResponse(tarea));
    }

    // HU-13: modificar tarea (con gestión de asignación HU-16)
    @Transactional
    @PutMapping("/{tareaId}")
    public ResponseEntity<ModificarTareaResponse> modificar(
            @PathVariable Long tareaId,
            @RequestHeader("X-Usuario-Id") Long jefeId,
            @RequestBody ModificarTareaRequest request) {

        boolean limpiar = Boolean.TRUE.equals(request.getLimpiarAsignacion());

        ModificarTareaCommand command = new ModificarTareaCommand(
            tareaId,
            jefeId,
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaLimite(),
            request.getUsuarioAsignadoId(),
            limpiar
        );

        Tarea tarea = modificarTareaUseCase.ejecutar(command);
        ModificarTareaResponse response = new ModificarTareaResponse();
        response.setMensaje("La tarea se ha modificado correctamente");
        response.setTarea(presenter.toResponse(tarea));
        return ResponseEntity.ok(response);
    }

    // HU-12: ver detalle de la tarea
    @GetMapping("/{tareaId}")
    public ResponseEntity<TareaResponse> verDetalle(
            @PathVariable Long tareaId,
            @RequestParam Long grupoId) {

        Tarea tarea = verDetalleTareaUseCase.ejecutar(tareaId, grupoId);
        return ResponseEntity.ok(presenter.toResponse(tarea));
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<TareaResponse>> listarPorGrupo(
            @PathVariable Long grupoId) {

        List<Tarea> tareas = listarTareasPorGrupoUseCase.ejecutar(grupoId);
        List<TareaResponse> response = tareas.stream()
            .map(presenter::toResponse)
            .toList();
        return ResponseEntity.ok(response);
    }

    // HU-06 CA-01: listar tareas asignadas al miembro en sesión
    @GetMapping("/asignadas")
    public ResponseEntity<List<TareaResponse>> listarAsignadas(
            @RequestHeader("X-Usuario-Id") Long miembroId) {

        List<Tarea> tareas = listarTareasAsignadasUseCase.ejecutar(miembroId);
        List<TareaResponse> response = tareas.stream()
            .map(presenter::toResponse)
            .toList();
        return ResponseEntity.ok(response);
    }

    // HU-06 CA-02: actualizar estado de una tarea asignada
    @Transactional
    @PatchMapping("/{tareaId}/estado")
    public ResponseEntity<ModificarTareaResponse> actualizarEstado(
            @PathVariable Long tareaId,
            @RequestHeader("X-Usuario-Id") Long miembroId,
            @RequestBody ActualizarEstadoRequest request) {

        ActualizarEstadoTareaCommand command = new ActualizarEstadoTareaCommand(
            tareaId,
            miembroId,
            request.getNuevoEstado()
        );
        Tarea tarea = actualizarEstadoTareaUseCase.ejecutar(command);
        ModificarTareaResponse response = new ModificarTareaResponse();
        response.setMensaje("El estado de la tarea se ha actualizado correctamente");
        response.setTarea(presenter.toResponse(tarea));
        return ResponseEntity.ok(response);
    }

    // HU-15: eliminar tarea
    @Transactional
    @DeleteMapping("/{tareaId}")
    public ResponseEntity<MensajeResponse> eliminar(
            @PathVariable Long tareaId,
            @RequestHeader("X-Usuario-Id") Long jefeId) {

        eliminarTareaUseCase.ejecutar(new EliminarTareaCommand(tareaId, jefeId));
        return ResponseEntity.ok(new MensajeResponse("La tarea ha sido eliminada correctamente"));
    }
}
