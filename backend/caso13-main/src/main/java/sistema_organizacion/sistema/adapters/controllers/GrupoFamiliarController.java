package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_organizacion.sistema.adapters.dto.request.CrearGrupoRequest;
import sistema_organizacion.sistema.adapters.dto.response.GrupoFamiliarResponse;
import sistema_organizacion.sistema.adapters.presenters.GrupoFamiliarPresenter;
import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoCommand;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;

@RestController
@RequestMapping("/api/grupos")
public class GrupoFamiliarController {

    private final CrearGrupoFamiliarUseCase crearGrupoUseCase;
    private final GrupoFamiliarPresenter presenter;

    public GrupoFamiliarController(CrearGrupoFamiliarUseCase crearGrupoUseCase,
                                    GrupoFamiliarPresenter presenter) {
        this.crearGrupoUseCase = crearGrupoUseCase;
        this.presenter = presenter;
    }

    // CA-04-A HU-02: creación exitosa
    // CA-03-B HU-02: respuesta con código único
    @PostMapping
    public ResponseEntity<GrupoFamiliarResponse> crear(
            @RequestBody CrearGrupoRequest request,
            @RequestHeader("X-Usuario-Id") String jefeId) {

        CrearGrupoCommand command = new CrearGrupoCommand(
            request.getNombre(), jefeId
        );
        GrupoFamiliar grupo = crearGrupoUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(presenter.toResponse(grupo));
    }
}