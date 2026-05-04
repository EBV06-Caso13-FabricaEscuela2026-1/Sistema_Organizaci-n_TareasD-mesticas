package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_organizacion.sistema.entities.DetalleTarea;
import sistema_organizacion.sistema.ports.outs.DetalleTareaOutputPort;
import sistema_organizacion.sistema.usecases.grupo.CrearDetalleTareaCommand;
import sistema_organizacion.sistema.usecases.usuario.impl.CrearDetalleTareaInteractor;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-tareas")
public class DetalleTareaController {

    private final CrearDetalleTareaInteractor crearDetalleTareaInteractor;
    private final DetalleTareaOutputPort detalleTareaOutputPort;

    public DetalleTareaController(CrearDetalleTareaInteractor crearDetalleTareaInteractor,
                            DetalleTareaOutputPort detalleTareaOutputPort) {
        this.crearDetalleTareaInteractor = crearDetalleTareaInteractor;
        this.detalleTareaOutputPort = detalleTareaOutputPort;
    }

    @PostMapping
    public ResponseEntity<DetalleTarea> crear(@RequestBody CrearDetalleTareaCommand command) {
        return ResponseEntity.ok(crearDetalleTareaInteractor.ejecutar(command));
    }

    @GetMapping("/tarea/{tareaId}")
    public ResponseEntity<List<DetalleTarea>> listarPorTarea(@PathVariable Long tareaId) {
        return ResponseEntity.ok(detalleTareaOutputPort.buscarPorTareaId(tareaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTarea> buscarPorId(@PathVariable Long id) {
        return detalleTareaOutputPort.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    
}