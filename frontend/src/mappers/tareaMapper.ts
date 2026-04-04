import type { CrearTareaRequestDto, TareaResponseDto } from '../api/types';

export type TaskStatusUi = 'pendiente' | 'en proceso' | 'terminada';

export interface TaskUi {
  id: string;
  name: string;
  description: string;
  dueDate: string;
  assignedTo: string;
  status: TaskStatusUi;
}

/** Formulario de creación de tarea (UI) → body POST /api/tareas. */
export function mapFormCreacionToCrearTareaRequest(
  form: {
    name: string;
    description: string;
    dueDate: string;
  },
  grupoId: string
): CrearTareaRequestDto {
  return {
    titulo: form.name.trim(),
    descripcion: form.description?.trim() ?? '',
    fechaLimite: form.dueDate,
    grupoId,
  };
}

/** Respuesta GET/POST tarea → modelo de pantallas. */
export function mapTareaResponseToTaskUi(dto: TareaResponseDto): TaskUi {
  return {
    id: dto.id,
    name: dto.titulo,
    description: dto.descripcion ?? '',
    dueDate: dto.fechaLimite,
    assignedTo: dto.miembroAsignado ?? '',
    status: mapEstadoBackendToUi(dto.estado),
  };
}

function mapEstadoBackendToUi(estado: string): TaskStatusUi {
  switch (estado) {
    case 'PENDIENTE':
      return 'pendiente';
    case 'EN_PROCESO':
      return 'en proceso';
    case 'TERMINADA':
      return 'terminada';
    default:
      return 'pendiente';
  }
}
