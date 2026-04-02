import { Header } from '../components/Header';
import { Button } from '../components/Button';
import { StatusBadge } from '../components/StatusBadge';

type TaskStatus = 'pendiente' | 'en proceso' | 'terminada';

interface Task {
  id: string;
  name: string;
  description: string;
  dueDate: string;
  assignedTo: string;
  status: TaskStatus;
}

interface TaskDetailScreenProps {
  task: Task;
  onBack: () => void;
  onStatusChange: (taskId: string, newStatus: TaskStatus) => void;
}

export function TaskDetailScreen({ task, onBack }: TaskDetailScreenProps) {

  const formatDate = (dateString: string): string => {
    if (!dateString) return 'Sin fecha';
    const date = new Date(dateString);
    const options: Intl.DateTimeFormatOptions = {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    };
    return date.toLocaleDateString('es-ES', options);
  };

  return (
    <div className="min-h-screen bg-white flex flex-col">
      <Header title="Detalle Tarea" showBack onBack={onBack} />

      <div className="flex-1 px-6 py-6 space-y-6">
        <div>
          <label className="block text-[#757575] text-sm font-medium mb-2 uppercase tracking-wide">
            Nombre de la tarea
          </label>
          <div className="px-4 py-3 bg-teal-50 rounded-xl text-[#212121] font-medium">
            {task.name}
          </div>
        </div>

        <div>
          <label className="block text-[#757575] text-sm font-medium mb-2 uppercase tracking-wide">
            Estado
          </label>
          <div>
            <StatusBadge status="pendiente" />
          </div>
        </div>

        <div>
          <label className="block text-[#757575] text-sm font-medium mb-2 uppercase tracking-wide">
            Descripción
          </label>
          <div className="px-4 py-3 bg-blue-50 rounded-xl text-[#212121]">
            {task.description || <span className="text-[#757575]">sin descripción</span>}
          </div>
        </div>

        <div>
          <label className="block text-[#757575] text-sm font-medium mb-2 uppercase tracking-wide">
            Fecha límite
          </label>
          <div className="px-4 py-3 bg-purple-50 rounded-xl text-[#212121] font-medium">
            {formatDate(task.dueDate)}
          </div>
        </div>

        <div>
          <label className="block text-[#757575] text-sm font-medium mb-2 uppercase tracking-wide">
            Asignado a
          </label>
          <div className="px-4 py-3 bg-green-50 rounded-xl text-[#212121] font-medium">
            {task.assignedTo || <span className="text-[#757575] italic">Sin integrante asignado</span>}
          </div>
        </div>
      </div>

      <div className="px-6 pb-6">
        <Button onClick={onBack}>Volver a la lista</Button>
      </div>
    </div>
  );
}
