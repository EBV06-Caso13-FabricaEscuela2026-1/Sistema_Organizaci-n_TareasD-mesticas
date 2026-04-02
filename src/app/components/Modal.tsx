import { X } from 'lucide-react';

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  title: string;
  children: React.ReactNode;
  showCloseButton?: boolean;
}

export function Modal({ isOpen, onClose, title, children, showCloseButton = true }: ModalProps) {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50 p-4">
      <div className="bg-white rounded-xl max-w-md w-full p-6">
        <div className="flex items-start justify-between mb-4">
          <h2 className="font-bold text-lg text-[#212121]">{title}</h2>
          {showCloseButton && (
            <button onClick={onClose} className="text-[#757575]">
              <X size={24} />
            </button>
          )}
        </div>
        <div>{children}</div>
      </div>
    </div>
  );
}
