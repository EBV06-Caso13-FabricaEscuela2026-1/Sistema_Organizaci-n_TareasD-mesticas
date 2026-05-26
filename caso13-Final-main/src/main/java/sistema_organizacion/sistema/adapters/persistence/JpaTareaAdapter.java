package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.infrastructure.repositories.EstadoRepository;
import sistema_organizacion.sistema.infrastructure.repositories.TareaRepository;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;

@Component
public class JpaTareaAdapter implements TareaOutputPort {

    private final TareaRepository tareaRepository;
    private final EstadoRepository estadoRepository;

    public JpaTareaAdapter(
            TareaRepository tareaRepository,
            EstadoRepository estadoRepository) {
        this.tareaRepository = tareaRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(
            Objects.requireNonNull(tarea, "La tarea no puede ser null")
        );
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(
            Objects.requireNonNull(id, "El id no puede ser null")
        );
    }

    @Override
    public Estado buscarEstadoPorNombre(EstadoTarea nombreEstado) {
        return estadoRepository.findByNombreEstado(nombreEstado)
            .orElseThrow(() -> new RuntimeException(
                "Estado no encontrado: " + nombreEstado
            ));
    }

    @Override
    public List<Tarea> findByGrupoId(Long grupoId) {
        return tareaRepository.findByGrupo_Id(grupoId);
    }

    @Override
    public List<Tarea> findByUsuarioId(Long usuarioId) {
        return tareaRepository.findByUsuario_Id(
            Objects.requireNonNull(usuarioId, "El id de usuario no puede ser null")
        );
    }

    @Override
    public void eliminar(Long id) {
        tareaRepository.deleteById(
            Objects.requireNonNull(id, "El id no puede ser null")
        );
    }
}