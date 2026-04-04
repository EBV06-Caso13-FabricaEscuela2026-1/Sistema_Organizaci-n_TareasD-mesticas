/** Formato devuelto por el backend (JSON). */
export interface GrupoFamiliarResponseDto {
  id: string;
  nombre: string;
  codigoAcceso: string;
  fechaCreacion: string;
}

export interface CrearGrupoRequestDto {
  nombre: string;
}

export interface CrearTareaRequestDto {
  titulo: string;
  descripcion: string;
  fechaLimite: string;
  grupoId: string;
}

export interface TareaResponseDto {
  id: string;
  titulo: string;
  descripcion: string;
  fechaLimite: string;
  estado: string;
  miembroAsignado: string;
  grupoId: string;
}
