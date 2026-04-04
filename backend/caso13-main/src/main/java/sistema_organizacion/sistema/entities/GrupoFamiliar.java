package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrupoFamiliar {
    private String id;
    private String nombre;
    private String codigoAcceso;
    private String jefeId;
    private LocalDate fechaCreacion;
    private List<MiembroHogar> miembros;

    public GrupoFamiliar(String id, String nombre,
                         String codigoAcceso, String jefeId) {
        this.id = id;
        this.nombre = nombre;
        this.codigoAcceso = codigoAcceso;
        this.jefeId = jefeId;
        this.fechaCreacion = LocalDate.now();
        this.miembros = new ArrayList<>();
    }

    public void agregarMiembro(MiembroHogar miembro) {
        boolean yaExiste = miembros.stream()
            .anyMatch(m -> m.getId().equals(miembro.getId()));
        if (yaExiste) {
            throw new IllegalStateException("El miembro ya pertenece al grupo");
        }
        miembros.add(miembro);
    }

    public List<MiembroHogar> getMiembros() {
        return Collections.unmodifiableList(miembros);
    }

    public String getId()           { return id; }
    public String getNombre()       { return nombre; }
    public String getCodigoAcceso() { return codigoAcceso; }
    public String getJefeId()       { return jefeId; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }

    public void setId(String id)    { this.id = id; }
}