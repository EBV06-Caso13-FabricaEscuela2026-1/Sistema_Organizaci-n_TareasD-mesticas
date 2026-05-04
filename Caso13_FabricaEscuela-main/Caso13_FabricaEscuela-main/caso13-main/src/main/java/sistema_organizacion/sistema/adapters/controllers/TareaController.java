package sistema_organizacion.sistema.adapters.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistema_organizacion.sistema.adapters.dto.request.CrearTareaRequest;
import sistema_organizacion.sistema.adapters.dto.request.ModificarTareaRequest;
import sistema_organizacion.sistema.adapters.dto.response.ModificarTareaResponse;
import sistema_organizacion.sistema.adapters.dto.response.TareaResponse;
import sistema_organizacion.sistema.adapters.presenters.TareaPresenter;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;
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
    private final TareaPresenter presenter;
    private final ListarTareasPorGrupoUseCase listarTareasPorGrupoUseCase;

    public TareaController(
    CrearTareaUseCase crearTareaUseCase,
    ModificarTareaUseCase modificarTareaUseCase,
    VerDetalleTareaUseCase verDetalleTareaUseCase,
    ListarTareasPorGrupoUseCase listarTareasPorGrupoUseCase,
    TareaPresenter presenter
) {
    this.crearTareaUseCase = crearTareaUseCase;
    this.modificarTareaUseCase = modificarTareaUseCase;
    this.verDetalleTareaUseCase = verDetalleTareaUseCase;
    this.listarTareasPorGrupoUseCase = listarTareasPorGrupoUseCase;
    this.presenter = presenter;
}

    // HU-11: crear tarea
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
            jefeId
        );
        Tarea tarea = crearTareaUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(presenter.toResponse(tarea));
    }

    // HU-13: modificar tarea
    @Transactional
    @PutMapping("/{tareaId}")
    public ResponseEntity<ModificarTareaResponse> modificar(
            @PathVariable Long tareaId,
            @RequestHeader("X-Usuario-Id") Long jefeId,
            @RequestBody ModificarTareaRequest request) {

        ModificarTareaCommand command = new ModificarTareaCommand(
            tareaId,
            jefeId,
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaLimite()
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
}