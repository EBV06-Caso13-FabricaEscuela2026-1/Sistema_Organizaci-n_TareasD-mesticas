package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_organizacion.sistema.adapters.dto.request.LoginRequest;
import sistema_organizacion.sistema.adapters.dto.request.RegistroRequest;
import sistema_organizacion.sistema.adapters.dto.response.SesionResponse;
import sistema_organizacion.sistema.adapters.dto.response.UsuarioResponse;
import sistema_organizacion.sistema.adapters.presenters.UsuarioPresenter;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.usecases.usuario.IniciarSesionCommand;
import sistema_organizacion.sistema.usecases.usuario.IniciarSesionUseCase;
import sistema_organizacion.sistema.usecases.usuario.RegistrarUsuarioCommand;
import sistema_organizacion.sistema.usecases.usuario.RegistrarUsuarioUseCase;
import sistema_organizacion.sistema.usecases.usuario.SesionResult;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final RegistrarUsuarioUseCase registrarUseCase;
    private final IniciarSesionUseCase iniciarSesionUseCase;
    private final UsuarioPresenter presenter;

    public UsuarioController(RegistrarUsuarioUseCase registrarUseCase,
                                IniciarSesionUseCase iniciarSesionUseCase,
                                UsuarioPresenter presenter) {
        this.registrarUseCase = registrarUseCase;
        this.iniciarSesionUseCase = iniciarSesionUseCase;
        this.presenter = presenter;
    }

    // HU-01: registro de usuario
    @Transactional
    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(
            @RequestBody RegistroRequest request) {

        RegistrarUsuarioCommand command = new RegistrarUsuarioCommand(
            request.getNombre(),
            request.getApellido(),
            request.getCorreo(),
            request.getUsername(),
            request.getContrasena(),
            RolUsuario.valueOf(request.getRol())
        );
        Usuario usuario = registrarUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(presenter.toResponse(usuario));
    }

    // HU-10: inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<SesionResponse> login(
            @RequestBody LoginRequest request) {

        IniciarSesionCommand command = new IniciarSesionCommand(
            request.getCorreo(),
            request.getContrasena()
        );
        SesionResult resultado = iniciarSesionUseCase.ejecutar(command);
        return ResponseEntity.ok(presenter.toSesionResponse(resultado));
    }
}