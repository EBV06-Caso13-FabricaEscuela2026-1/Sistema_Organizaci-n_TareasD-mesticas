import type { CrearGrupoRequestDto } from '../api/types';

/** Convierte el nombre mostrado en UI al body esperado por POST /api/grupos. */
export function mapNombreGrupoToCrearRequest(nombreGrupoUi: string): CrearGrupoRequestDto {
  return { nombre: nombreGrupoUi.trim() };
}
