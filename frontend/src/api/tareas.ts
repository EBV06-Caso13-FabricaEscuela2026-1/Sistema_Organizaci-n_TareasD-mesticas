import { apiFetch } from './client';
import type { CrearTareaRequestDto, TareaResponseDto } from './types';

export function crearTarea(body: CrearTareaRequestDto): Promise<TareaResponseDto> {
  return apiFetch<TareaResponseDto>('/api/tareas', {
    method: 'POST',
    body: JSON.stringify(body),
  });
}

export function obtenerDetalleTarea(tareaId: string, grupoId: string): Promise<TareaResponseDto> {
  const q = new URLSearchParams({ grupoId });
  return apiFetch<TareaResponseDto>(`/api/tareas/${encodeURIComponent(tareaId)}?${q.toString()}`, {
    method: 'GET',
  });
}
