import React, { useState } from 'react';
import { CreateGroupScreen } from './screens/CreateGroupScreen';
import { TaskListScreen } from './screens/TaskListScreen';
import { CreateTaskScreen } from './screens/CreateTaskScreen';
import { TaskDetailScreen } from './screens/TaskDetailScreen';
import { Toast } from './components/Toast';
import { buildTaskUiFromForm, type TaskUi } from '../mappers/tareaMapper';

type Screen = 'createGroup' | 'taskList' | 'createTask' | 'taskDetail';
type TaskStatus = 'pendiente' | 'en proceso' | 'terminada';

export default function App() {
  const [currentScreen, setCurrentScreen] = useState<Screen>('createGroup');
  const [groupName, setGroupName] = useState('');
  const [groupCode, setGroupCode] = useState('');
  const [grupoId, setGrupoId] = useState('');
  const [tasks, setTasks] = useState<TaskUi[]>([]);
  const [detailTask, setDetailTask] = useState<TaskUi | null>(null);
  const [isAdmin] = useState(true);
  const [showToast, setShowToast] = useState(false);
  const [toastMessage, setToastMessage] = useState('');

  const members = ['Juan pablo segundo', 'María García', 'Carlos López', 'Ana Martínez'];

  const showErrorToast = (message: string) => {
    setToastMessage(message);
    setShowToast(true);
  };

  const handleGroupCreated = (name: string, code: string, id: string) => {
    setGroupName(name);
    setGroupCode(code);
    setGrupoId(id);
    setCurrentScreen('taskList');
  };

  const handleCreateTask = () => {
    setCurrentScreen('createTask');
  };

  const handleTaskCreated = (taskData: {
    name: string;
    description: string;
    dueDate: string;
    assignedTo: string;
  }) => {
    if (!grupoId) {
      showErrorToast('No hay grupo seleccionado');
      return;
    }
    const task = buildTaskUiFromForm(taskData);
    setTasks((prev) => [...prev, task]);
    setCurrentScreen('taskList');
  };

  const handleTaskClick = (taskId: string) => {
    if (!grupoId) {
      showErrorToast('No hay grupo seleccionado');
      return;
    }
    const task = tasks.find((t) => t.id === taskId);
    if (!task) {
      showErrorToast('No se encontró la tarea');
      return;
    }
    setDetailTask(task);
    setCurrentScreen('taskDetail');
  };

  const handleStatusChange = (taskId: string, newStatus: TaskStatus) => {
    setTasks((prev) =>
      prev.map((task) => (task.id === taskId ? { ...task, status: newStatus } : task))
    );
    setDetailTask((prev) =>
      prev && prev.id === taskId ? { ...prev, status: newStatus } : prev
    );
  };

  const handleBackToList = () => {
    setCurrentScreen('taskList');
    setDetailTask(null);
  };

  return (
    <div className="w-full h-screen bg-white max-w-[390px] mx-auto shadow-xl overflow-hidden relative">
      {currentScreen === 'createGroup' && (
        <CreateGroupScreen
          onGroupCreated={handleGroupCreated}
          onCancel={() => {}}
          isAdmin={isAdmin}
        />
      )}

      {currentScreen === 'taskList' && (
        <TaskListScreen
          groupName={groupName}
          groupCode={groupCode}
          tasks={tasks}
          onCreateTask={handleCreateTask}
          onTaskClick={handleTaskClick}
        />
      )}

      {currentScreen === 'createTask' && (
        <CreateTaskScreen
          onBack={handleBackToList}
          onTaskCreated={handleTaskCreated}
          existingTaskNames={tasks.map((t) => t.name.toLowerCase())}
          members={members}
        />
      )}

      {currentScreen === 'taskDetail' && detailTask && (
        <TaskDetailScreen
          task={detailTask}
          onBack={handleBackToList}
          onStatusChange={handleStatusChange}
        />
      )}

      <Toast
        message={toastMessage}
        isVisible={showToast}
        onClose={() => setShowToast(false)}
        type="error"
      />
    </div>
  );
}
