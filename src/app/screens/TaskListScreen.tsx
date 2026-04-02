import { ClipboardList } from 'lucide-react';
import { Header } from '../components/Header';
import { Button } from '../components/Button';
import { TaskCard } from '../components/TaskCard';

interface Task {
  id: string;
  name: string;
}

interface TaskListScreenProps {
  groupName: string;
  groupCode: string;
  tasks: Task[];
  onCreateTask: () => void;
  onTaskClick: (taskId: string) => void;
}

export function TaskListScreen({ groupName, groupCode, tasks, onCreateTask, onTaskClick }: TaskListScreenProps) {
  return (
    <div className="min-h-screen bg-white flex flex-col">
      <Header
        title="Mis Tareas"
        groupName={groupName}
        groupCode={groupCode}
      />

      <div className="flex-1 px-6 py-6">
        {tasks.length === 0 ? (
          <div className="flex flex-col items-center justify-center py-16">
            <ClipboardList size={48} className="text-[#757575] mb-4" />
            <h2 className="font-bold text-lg text-[#212121] mb-2">No hay Tareas</h2>
            <p className="text-[#757575] text-center px-8">
              Comienza creando tu primera tarea para organizar las actividades del hogar
            </p>
          </div>
        ) : (
          <div className="space-y-3">
            {tasks.map((task) => (
              <TaskCard
                key={task.id}
                taskName={task.name}
                onClick={() => onTaskClick(task.id)}
              />
            ))}
          </div>
        )}
      </div>

      <div className="px-6 pb-6">
        <Button onClick={onCreateTask}>+ Nueva Tarea</Button>
      </div>
    </div>
  );
}
