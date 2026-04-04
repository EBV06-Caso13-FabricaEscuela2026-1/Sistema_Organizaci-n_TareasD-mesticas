package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_organizacion.sistema.adapters.dto.request.CrearTareaRequest;
import sistema_organizacion.sistema.adapters.dto.response.TareaResponse;
import sistema_organizacion.sistema.adapters.presenters.TareaPresenter;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.VerDetalleTareaUseCase;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final CrearTareaUseCase crearTareaUseCase;
    private final VerDetalleTareaUseCase verDetalleTareaUseCase;
    private final TareaPresenter presenter;

    public TareaController(CrearTareaUseCase crearTareaUseCase,
                            VerDetalleTareaUseCase verDetalleTareaUseCase,
                            TareaPresenter presenter) {
        this.crearTareaUseCase = crearTareaUseCase;
        this.verDetalleTareaUseCase = verDetalleTareaUseCase;
        this.presenter = presenter;
    }

    // HU-11: crear tarea
    @PostMapping
    public ResponseEntity<TareaResponse> crear(
            @RequestBody CrearTareaRequest request,
            @RequestHeader("X-Usuario-Id") String jefeId) {

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

    // HU-12: ver detalle de la tarea
    @GetMapping("/{tareaId}")
    public ResponseEntity<TareaResponse> verDetalle(
            @PathVariable String tareaId,
            @RequestParam String grupoId) {

        Tarea tarea = verDetalleTareaUseCase.ejecutar(tareaId, grupoId);
        return ResponseEntity.ok(presenter.toResponse(tarea));
    }
}