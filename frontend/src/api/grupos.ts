import { apiFetch } from './client';
import type { CrearGrupoRequestDto, GrupoFamiliarResponseDto } from './types';

export function crearGrupo(body: CrearGrupoRequestDto): Promise<GrupoFamiliarResponseDto> {
  return apiFetch<GrupoFamiliarResponseDto>('/api/grupos', {
    method: 'POST',
    body: JSON.stringify(body),
  });
}
