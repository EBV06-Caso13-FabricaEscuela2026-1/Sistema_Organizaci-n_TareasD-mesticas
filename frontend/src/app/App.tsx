import React, { useState } from 'react';
import { CreateGroupScreen } from './screens/CreateGroupScreen';
import { TaskListScreen } from './screens/TaskListScreen';
import { CreateTaskScreen } from './screens/CreateTaskScreen';
import { TaskDetailScreen } from './screens/TaskDetailScreen';
import { Toast } from './components/Toast';
import { crearTarea, obtenerDetalleTarea } from '../api/tareas';
import { ApiError } from '../api/client';
import {
  mapFormCreacionToCrearTareaRequest,
  mapTareaResponseToTaskUi,
  type TaskUi,
} from '../mappers/tareaMapper';

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

  const handleTaskCreated = async (taskData: {
    name: string;
    description: string;
    dueDate: string;
    assignedTo: string;
  }) => {
    if (!grupoId) {
      showErrorToast('No hay grupo seleccionado');
      return;
    }
    try {
      const body = mapFormCreacionToCrearTareaRequest(
        {
          name: taskData.name,
          description: taskData.description,
          dueDate: taskData.dueDate,
        },
        grupoId
      );
      const res = await crearTarea(body);
      const task = mapTareaResponseToTaskUi(res);
      setTasks((prev) => [...prev, task]);
      setCurrentScreen('taskList');
    } catch (e) {
      const msg = e instanceof ApiError ? e.message : 'No se pudo crear la tarea';
      showErrorToast(msg);
    }
  };

  const handleTaskClick = async (taskId: string) => {
    if (!grupoId) {
      showErrorToast('No hay grupo seleccionado');
      return;
    }
    const local = tasks.find((t) => t.id === taskId);
    try {
      const dto = await obtenerDetalleTarea(taskId, grupoId);
      const task = mapTareaResponseToTaskUi(dto);
      const merged: TaskUi = { ...task, status: local?.status ?? task.status };
      setDetailTask(merged);
      setCurrentScreen('taskDetail');
    } catch (e) {
      const msg = e instanceof ApiError ? e.message : 'No se pudo cargar la tarea';
      showErrorToast(msg);
    }
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
