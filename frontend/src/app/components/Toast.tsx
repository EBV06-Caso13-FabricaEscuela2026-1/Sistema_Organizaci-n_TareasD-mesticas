import { useEffect } from 'react';
import { X } from 'lucide-react';

interface ToastProps {
  message: string;
  isVisible: boolean;
  onClose: () => void;
  type?: 'error' | 'success' | 'info';
}

export function Toast({ message, isVisible, onClose, type = 'info' }: ToastProps) {
  useEffect(() => {
    if (isVisible) {
      const timer = setTimeout(() => {
        onClose();
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [isVisible, onClose]);

  if (!isVisible) return null;

  const bgColors = {
    error: 'bg-red-500',
    success: 'bg-green-500',
    info: 'bg-[#757575]'
  };

  return (
    <div className="fixed top-4 left-4 right-4 z-50 animate-slide-down">
      <div className={`${bgColors[type]} text-white px-4 py-3 rounded-xl shadow-lg flex items-center justify-between`}>
        <span className="font-medium">{message}</span>
        <button onClick={onClose} className="ml-4 p-1">
          <X size={20} />
        </button>
      </div>
    </div>
  );
}
