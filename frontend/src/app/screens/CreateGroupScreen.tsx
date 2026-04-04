import { useState } from 'react';
import { Users } from 'lucide-react';
import { Button } from '../components/Button';
import { Input } from '../components/Input';
import { Modal } from '../components/Modal';
import { crearGrupo } from '../../api/grupos';
import { mapNombreGrupoToCrearRequest } from '../../mappers/grupoMapper';

interface CreateGroupScreenProps {
  onGroupCreated: (groupName: string, groupCode: string, grupoId: string) => void;
  onCancel: () => void;
  isAdmin: boolean;
}

export function CreateGroupScreen({ onGroupCreated, onCancel, isAdmin }: CreateGroupScreenProps) {
  const [groupName, setGroupName] = useState('');
  const [error, setError] = useState('');
  const [showErrorModal, setShowErrorModal] = useState(false);
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  const [generatedCode, setGeneratedCode] = useState('');
  const [pendingGrupoId, setPendingGrupoId] = useState('');
  const [submitting, setSubmitting] = useState(false);

  const validateGroupName = (name: string): string | null => {
    if (name.length < 6 || name.length > 25) {
      return 'El nombre debe tener entre 6 y 25 caracteres';
    }
    const validPattern = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]+$/;
    if (!validPattern.test(name)) {
      return 'Solo se permiten letras, números, espacios, tildes y ñ';
    }
    return null;
  };

  const handleSubmit = async () => {
    if (!isAdmin) {
      setShowErrorModal(true);
      return;
    }

    const validationError = validateGroupName(groupName);
    if (validationError) {
      setError(validationError);
      return;
    }

    setSubmitting(true);
    setError('');
    try {
      const body = mapNombreGrupoToCrearRequest(groupName);
      const res = await crearGrupo(body);
      setGeneratedCode(res.codigoAcceso);
      setPendingGrupoId(res.id);
      setShowSuccessModal(true);
    } catch (e) {
      const msg = e instanceof Error ? e.message : 'No se pudo crear el grupo';
      setError(msg);
    } finally {
      setSubmitting(false);
    }
  };

  const handleSuccessConfirm = () => {
    setShowSuccessModal(false);
    onGroupCreated(groupName, generatedCode, pendingGrupoId);
  };

  const handleChange = (value: string) => {
    setGroupName(value);
    if (value) {
      const validationError = validateGroupName(value);
      setError(validationError || '');
    } else {
      setError('');
    }
  };

  return (
    <div className="min-h-screen bg-white flex flex-col">
      <div className="flex-1 px-6 py-8 flex flex-col">
        <h1 className="font-bold text-xl text-[#212121] mb-6">Creación del grupo familiar</h1>

        <div className="flex justify-center mb-6">
          <div className="w-20 h-20 bg-[#F5F5F5] rounded-full flex items-center justify-center">
            <Users size={40} className="text-[#757575]" />
          </div>
        </div>

        <p className="text-[#757575] text-center mb-8">
          Crea un grupo para gestionar las tareas del hogar con tu familia
        </p>

        <div className="mb-6">
          <Input
            label="Nombre del grupo"
            value={groupName}
            onChange={handleChange}
            placeholder="Mi familia"
            error={error}
            required
          />
        </div>

        <div className="bg-[#F5F5F5] p-4 rounded-xl mb-8">
          <p className="font-medium text-[#212121] mb-2">Requisitos:</p>
          <ul className="text-sm text-[#757575] space-y-1">
            <li>• Entre 6 y 25 caracteres</li>
            <li>• Solo letras, números, espacios, tildes, ñ</li>
          </ul>
        </div>

        <div className="mt-auto space-y-3">
          <Button onClick={handleSubmit} disabled={submitting}>
            {submitting ? 'Creando…' : 'Crear grupo familiar'}
          </Button>
          <Button variant="secondary" onClick={onCancel} disabled={submitting}>Cancelar</Button>
        </div>
      </div>

      <Modal
        isOpen={showErrorModal}
        onClose={() => setShowErrorModal(false)}
        title="Error de permisos"
      >
        <p className="text-[#757575] mb-4">No tienes permisos para crear grupos</p>
        <Button onClick={() => setShowErrorModal(false)}>Aceptar</Button>
      </Modal>

      <Modal
        isOpen={showSuccessModal}
        onClose={() => {}}
        title="¡Grupo creado!"
        showCloseButton={false}
      >
        <p className="text-[#757575] mb-2">Tu grupo ha sido creado exitosamente</p>
        <p className="text-[#212121] font-bold text-lg mb-4">Código: {generatedCode}</p>
        <p className="text-sm text-[#757575] mb-4">Comparte este código con los miembros de tu familia para que puedan unirse</p>
        <Button onClick={handleSuccessConfirm}>Aceptar</Button>
      </Modal>
    </div>
  );
}
