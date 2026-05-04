package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_organizacion.sistema.adapters.dto.request.CrearGrupoRequest;
import sistema_organizacion.sistema.adapters.dto.request.IngresarGrupoRequest;
import sistema_organizacion.sistema.adapters.dto.response.GrupoFamiliarResponse;
import sistema_organizacion.sistema.adapters.presenters.GrupoFamiliarPresenter;
import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoCommand;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoCommand;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.ListarGruposUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoFamiliarController {

    private final CrearGrupoFamiliarUseCase crearGrupoFamiliarUseCase;
    private final IngresarGrupoFamiliarUseCase ingresarGrupoFamiliarUseCase;
    private final GrupoFamiliarPresenter presenter;
    private final ListarGruposUseCase listarGruposUseCase;

    public GrupoFamiliarController(
        CrearGrupoFamiliarUseCase crearGrupoFamiliarUseCase,
        IngresarGrupoFamiliarUseCase ingresarGrupoFamiliarUseCase,
        ListarGruposUseCase listarGruposUseCase,
        GrupoFamiliarPresenter presenter) {
    
    this.crearGrupoFamiliarUseCase = crearGrupoFamiliarUseCase;
    this.ingresarGrupoFamiliarUseCase = ingresarGrupoFamiliarUseCase;
    this.listarGruposUseCase = listarGruposUseCase;
    this.presenter = presenter;
}

    @Transactional
    @PostMapping
    public ResponseEntity<GrupoFamiliarResponse> crear(
            @RequestBody CrearGrupoRequest request,
            @RequestHeader("X-Usuario-Id") Long jefeId) {

        CrearGrupoCommand command = new CrearGrupoCommand(
            request.getNombre(), jefeId
        );
        GrupoFamiliar grupo = crearGrupoFamiliarUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(presenter.toResponse(grupo));
    }

    @Transactional
    @PostMapping("/ingresar")
    public ResponseEntity<GrupoFamiliarResponse> ingresar(
            @RequestHeader("X-Usuario-Id") Long miembroId,
            @RequestBody IngresarGrupoRequest request) {

        IngresarGrupoCommand command = new IngresarGrupoCommand(
            miembroId,
            request.getCodigoAcceso()
        );
        GrupoFamiliar grupo = ingresarGrupoFamiliarUseCase.ejecutar(command);
        return ResponseEntity.ok(presenter.toResponse(grupo));
    }

    @GetMapping
    public ResponseEntity<List<GrupoFamiliarResponse>> listarGrupos() {
        List<GrupoFamiliar> grupos = listarGruposUseCase.ejecutar();

        List<GrupoFamiliarResponse> response = grupos.stream()
            .map(presenter::toResponse)
            .toList();

        return ResponseEntity.ok(response);
}
}