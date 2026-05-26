package sistema_organizacion.sistema.usecases.grupo;

import java.util.List;

import org.springframework.stereotype.Service;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;

@Service
public class ListarGruposUseCase {

    private final GrupoFamiliarOutputPort grupoRepository;

    public ListarGruposUseCase(GrupoFamiliarOutputPort grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public List<GrupoFamiliar> ejecutar() {
        return grupoRepository.listarTodos();
    }
}
