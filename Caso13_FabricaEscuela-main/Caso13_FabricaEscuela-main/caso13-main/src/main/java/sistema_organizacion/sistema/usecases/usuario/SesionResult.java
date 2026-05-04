package sistema_organizacion.sistema.usecases.usuario;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.Rol;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;

public class SesionResult {
    private final Usuario usuario;
    private final Rol rol;
    private final GrupoFamiliar grupo;
    private final String redireccion;

    public SesionResult(Usuario usuario, GrupoFamiliar grupo) {
        this.usuario = usuario;
        this.rol = usuario.getRol();
        this.grupo = grupo;

        if (rol.getNombreRol() == RolUsuario.ADMIN) {
            this.redireccion = "/panel/admin";
        } else if (grupo != null) {
            this.redireccion = "/panel/tareas";
        } else {
            this.redireccion = "/grupo/buscar";
        }
    }

    public Usuario getUsuario()       { return usuario; }
    public Rol getRol()               { return rol; }
    public GrupoFamiliar getGrupo()   { return grupo; }
    public String getRedireccion()    { return redireccion; }
}
