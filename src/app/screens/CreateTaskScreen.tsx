import { useState } from 'react';
import { Calendar } from 'lucide-react';
import { Header } from '../components/Header';
import { Button } from '../components/Button';
import { Input } from '../components/Input';
import { TextArea } from '../components/TextArea';

interface CreateTaskScreenProps {
  onBack: () => void;
  onTaskCreated: (task: {
    name: string;
    description: string;
    dueDate: string;
    assignedTo: string;
  }) => void;
  existingTaskNames: string[];
  members: string[];
}

export function CreateTaskScreen({ onBack, onTaskCreated, existingTaskNames, members }: CreateTaskScreenProps) {
  const [taskName, setTaskName] = useState('');
  const [description, setDescription] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [assignedTo, setAssignedTo] = useState('');
  const [errors, setErrors] = useState<{
    name?: string;
    description?: string;
    dueDate?: string;
  }>({});

  const validateTaskName = (name: string): string | null => {
    if (name.length < 3 || name.length > 60) {
      return 'El nombre debe tener entre 3 y 60 caracteres';
    }
    const validPattern = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]+$/;
    if (!validPattern.test(name)) {
      return 'Solo se permiten letras, números, espacios, tildes y ñ';
    }
    if (existingTaskNames.includes(name.toLowerCase())) {
      return 'Este nombre ya está en uso, ingresa uno diferente';
    }
    return null;
  };

  const validateDescription = (desc: string): string | null => {
    if (!desc) return null;
    const wordCount = desc.trim().split(/\s+/).length;
    if (wordCount > 100) {
      return 'La descripción no puede superar las 100 palabras';
    }
    return null;
  };

  const validateDate = (date: string): string | null => {
    if (!date) return null;
    const selectedDate = new Date(date);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    if (selectedDate < today) {
      return 'La fecha no puede estar en el pasado';
    }
    return null;
  };

  const handleNameChange = (value: string) => {
    setTaskName(value);
    if (value) {
      const error = validateTaskName(value);
      setErrors(prev => ({ ...prev, name: error || undefined }));
    } else {
      setErrors(prev => ({ ...prev, name: undefined }));
    }
  };

  const handleDescriptionChange = (value: string) => {
    setDescription(value);
    const error = validateDescription(value);
    setErrors(prev => ({ ...prev, description: error || undefined }));
  };

  const handleDateChange = (value: string) => {
    setDueDate(value);
    const error = validateDate(value);
    setErrors(prev => ({ ...prev, dueDate: error || undefined }));
  };

  const handleSubmit = () => {
    const nameError = validateTaskName(taskName);
    const descError = validateDescription(description);
    const dateError = validateDate(dueDate);

    if (nameError || descError || dateError || !taskName || !dueDate) {
      setErrors({
        name: nameError || (!taskName ? 'El nombre es obligatorio' : undefined),
        description: descError || undefined,
        dueDate: dateError || (!dueDate ? 'La fecha límite es obligatoria' : undefined),
      });
      return;
    }

    onTaskCreated({
      name: taskName,
      description,
      dueDate,
      assignedTo,
    });
  };

  return (
    <div className="min-h-screen bg-white flex flex-col">
      <Header title="Nueva Tarea" showBack onBack={onBack} />

      <div className="flex-1 px-6 py-6 overflow-y-auto">
        <div className="space-y-6">
          <div>
            <Input
              label="Nombre de la tarea"
              value={taskName}
              onChange={handleNameChange}
              placeholder="Ej: lavar los platos"
              error={errors.name}
              maxLength={60}
              showCounter
              required
            />
          </div>

          <div>
            <TextArea
              label="Descripción (Opcional)"
              value={description}
              onChange={handleDescriptionChange}
              placeholder="Detalles adicionales sobre la tarea..."
              error={errors.description}
              maxWords={100}
            />
          </div>

          <div>
            <label className="block text-[#212121] font-medium mb-2">
              Fecha límite <span className="text-red-500">*</span>
            </label>
            <div className="relative">
              <input
                type="date"
                value={dueDate}
                onChange={(e) => handleDateChange(e.target.value)}
                className="w-full px-4 py-3 bg-[#F5F5F5] border border-[#E0E0E0] rounded-xl text-[#212121]"
              />
              <Calendar className="absolute right-4 top-1/2 -translate-y-1/2 text-[#757575] pointer-events-none" size={20} />
            </div>
            {errors.dueDate && <p className="text-red-500 text-sm mt-1">{errors.dueDate}</p>}
          </div>

          <div>
            <label className="block text-[#212121] font-medium mb-2">Asignar a</label>
            <select
              value={assignedTo}
              onChange={(e) => setAssignedTo(e.target.value)}
              className="w-full px-4 py-3 bg-[#F5F5F5] border border-[#E0E0E0] rounded-xl text-[#212121]"
            >
              <option value="">Seleccionar miembro</option>
              {members.map((member, index) => (
                <option key={index} value={member}>{member}</option>
              ))}
            </select>
          </div>
        </div>
      </div>

      <div className="px-6 pb-6">
        <Button onClick={handleSubmit}>+ Crear Tarea</Button>
      </div>

      <div className="fixed bottom-20 right-6 w-12 h-12 bg-[#00BFA5] rounded-full flex items-center justify-center text-white font-bold shadow-lg">
        A
      </div>
    </div>
  );
}
