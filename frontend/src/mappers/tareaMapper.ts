export type TaskStatusUi = 'pendiente' | 'en proceso' | 'terminada';

export interface TaskUi {
  id: string;
  name: string;
  description: string;
  dueDate: string;
  assignedTo: string;
  status: TaskStatusUi;
}

export function buildTaskUiFromForm(form: {
  name: string;
  description: string;
  dueDate: string;
  assignedTo: string;
}): TaskUi {
  return {
    id: crypto.randomUUID(),
    name: form.name.trim(),
    description: form.description?.trim() ?? '',
    dueDate: form.dueDate,
    assignedTo: form.assignedTo ?? '',
    status: 'pendiente',
  };
}
