package sistema_organizacion.sistema.adapters.persistence;

import org.springframework.stereotype.Component;
import sistema_organizacion.sistema.entities.DetalleTarea;
import sistema_organizacion.sistema.infrastructure.repositories.DetalleTareaRepository;
import sistema_organizacion.sistema.ports.outs.DetalleTareaOutputPort;
import java.util.List;
import java.util.Optional;

@Component
public class JpaDetalleTareaAdapter implements DetalleTareaOutputPort {

    private final DetalleTareaRepository detalleTareaRepository;

    public JpaDetalleTareaAdapter(DetalleTareaRepository detalleTareaRepository) {
        this.detalleTareaRepository = detalleTareaRepository;
    }

    @Override
    @SuppressWarnings("null")
    public DetalleTarea guardar(DetalleTarea detalle) {
        return detalleTareaRepository.save(detalle);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<DetalleTarea> buscarPorId(Long id) {
        return detalleTareaRepository.findById(id);
    }

    @Override
    public List<DetalleTarea> buscarPorTareaId(Long tareaId) {
        return detalleTareaRepository.findByTarea_Id(tareaId);
    }
}