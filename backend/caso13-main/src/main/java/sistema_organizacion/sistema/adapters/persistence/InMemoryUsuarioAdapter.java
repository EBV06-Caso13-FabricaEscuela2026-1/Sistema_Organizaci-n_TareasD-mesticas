package sistema_organizacion.sistema.adapters.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.MiembroHogar;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;

@Component
public class InMemoryUsuarioAdapter implements UsuarioOutputPort {

    private final Map<String, Usuario> usuarios = new ConcurrentHashMap<>();

    public InMemoryUsuarioAdapter() {
        guardar(new JefeDeHogar(
            "jefe-1", "Ana", "Lopez", "ana@sistema.local", "ana", "1234"
        ));
        guardar(new MiembroHogar(
            "miembro-1", "Luis", "Perez", "luis@sistema.local", "luis", "1234"
        ));
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarios.values().stream()
            .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
            .findFirst();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }
}
