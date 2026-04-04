package sistema_organizacion.sistema.entities;

public class MiembroHogar extends Usuario {
    private String grupoFamiliarId;

    public MiembroHogar(String id, String nombre, String apellido,
                        String correo, String username, String contrasena) {
        super(id, nombre, apellido, correo, username, contrasena);
    }

    public void asignarAGrupo(String grupoId) {
        if (this.grupoFamiliarId != null) {
            throw new IllegalStateException("El miembro ya pertenece a un grupo");
        }
        this.grupoFamiliarId = grupoId;
    }

    public String getGrupoFamiliarId() { return grupoFamiliarId; }
}