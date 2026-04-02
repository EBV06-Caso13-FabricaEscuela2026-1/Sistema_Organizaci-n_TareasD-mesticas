import { ArrowLeft } from 'lucide-react';

interface HeaderProps {
  title?: string;
  groupName?: string;
  groupCode?: string;
  showBack?: boolean;
  onBack?: () => void;
}

export function Header({ title, groupName, groupCode, showBack, onBack }: HeaderProps) {
  if (groupName && groupCode) {
    return (
      <div className="bg-[#00BFA5] text-white px-4 py-4">
        <div>
          <h1 className="font-bold text-xl mb-1">{title || 'Mis Tareas'}</h1>
          <p className="text-sm opacity-90">Grupo: {groupName}</p>
          <p className="text-sm opacity-90">Código: {groupCode}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="bg-[#00BFA5] text-white px-4 py-4 flex items-center gap-3">
      {showBack && (
        <button onClick={onBack} className="p-1">
          <ArrowLeft size={24} />
        </button>
      )}
      <h1 className="font-bold text-xl">{title}</h1>
    </div>
  );
}
