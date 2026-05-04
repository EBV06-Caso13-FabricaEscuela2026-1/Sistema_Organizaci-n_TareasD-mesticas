package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.infrastructure.repositories.GrupoFamiliarRepository;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;

@Component
public class JpaGrupoFamiliarAdapter implements GrupoFamiliarOutputPort {

    private final GrupoFamiliarRepository grupoRepository;

    public JpaGrupoFamiliarAdapter(GrupoFamiliarRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    @SuppressWarnings("null")
    public GrupoFamiliar guardar(GrupoFamiliar grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public Optional<GrupoFamiliar> buscarPorId(Long id) {
        @SuppressWarnings("null")
        Optional<GrupoFamiliar> result = grupoRepository.findById(id);
        return result;
    }

    @Override
    public Optional<GrupoFamiliar> buscarPorCodigoAcceso(String codigo) {
        return grupoRepository.findByCodigoAcceso(codigo);
    }

    @Override
    public List<GrupoFamiliar> listarTodos() {
        return grupoRepository.findAll();
    }
}
