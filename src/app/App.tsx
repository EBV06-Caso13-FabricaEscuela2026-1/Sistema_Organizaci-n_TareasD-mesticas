import { useState, useEffect } from 'react';
import { CreateGroupScreen } from './screens/CreateGroupScreen';
import { TaskListScreen } from './screens/TaskListScreen';
import { CreateTaskScreen } from './screens/CreateTaskScreen';
import { TaskDetailScreen } from './screens/TaskDetailScreen';
import { Toast } from './components/Toast';

type Screen = 'createGroup' | 'taskList' | 'createTask' | 'taskDetail';
type TaskStatus = 'pendiente' | 'en proceso' | 'terminada';

interface Task {
  id: string;
  name: string;
  description: string;
  dueDate: string;
  assignedTo: string;
  status: TaskStatus;
}

export default function App() {
  const [currentScreen, setCurrentScreen] = useState<Screen>('createGroup');
  const [groupName, setGroupName] = useState('');
  const [groupCode, setGroupCode] = useState('');
  const [tasks, setTasks] = useState<Task[]>([]);
  const [selectedTaskId, setSelectedTaskId] = useState<string | null>(null);
  const [isAdmin] = useState(true); // Simular que el usuario es administrador
  const [showToast, setShowToast] = useState(false);
  const [toastMessage, setToastMessage] = useState('');

  const members = ['Juan pablo segundo', 'María García', 'Carlos López', 'Ana Martínez'];

  const handleGroupCreated = (name: string, code: string) => {
    setGroupName(name);
    setGroupCode(code);
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
    const newTask: Task = {
      id: Date.now().toString(),
      ...taskData,
      status: 'pendiente',
    };
    setTasks([...tasks, newTask]);
    setCurrentScreen('taskList');
  };

  const handleTaskClick = (taskId: string) => {
    const task = tasks.find(t => t.id === taskId);
    if (!task) {
      setToastMessage('Tarea no encontrada');
      setShowToast(true);
      return;
    }
    setSelectedTaskId(taskId);
    setCurrentScreen('taskDetail');
  };

  const handleStatusChange = (taskId: string, newStatus: TaskStatus) => {
    setTasks(tasks.map(task =>
      task.id === taskId ? { ...task, status: newStatus } : task
    ));
  };

  const handleBackToList = () => {
    setCurrentScreen('taskList');
    setSelectedTaskId(null);
  };

  const selectedTask = selectedTaskId ? tasks.find(t => t.id === selectedTaskId) : null;

  // HU-12 CA-02-A: Verificar si la tarea no existe
  useEffect(() => {
    if (currentScreen === 'taskDetail' && selectedTaskId && !selectedTask) {
      setToastMessage('Tarea no encontrada');
      setShowToast(true);
      setCurrentScreen('taskList');
      setSelectedTaskId(null);
    }
  }, [currentScreen, selectedTaskId, selectedTask]);

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
          existingTaskNames={tasks.map(t => t.name.toLowerCase())}
          members={members}
        />
      )}

      {currentScreen === 'taskDetail' && selectedTask && (
        <TaskDetailScreen
          task={selectedTask}
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
