type TaskStatus = 'pendiente' | 'en proceso' | 'terminada';

interface StatusBadgeProps {
  status: TaskStatus;
  onClick?: () => void;
}

const statusColors = {
  'pendiente': 'bg-yellow-100 text-yellow-800',
  'en proceso': 'bg-blue-100 text-blue-800',
  'terminada': 'bg-green-100 text-green-800'
};

export function StatusBadge({ status, onClick }: StatusBadgeProps) {
  const className = `inline-block px-4 py-2 rounded-full font-medium ${statusColors[status]} ${onClick ? 'cursor-pointer hover:opacity-80' : ''}`;

  if (onClick) {
    return (
      <button onClick={onClick} className={className}>
        {status}
      </button>
    );
  }

  return (
    <div className={className}>
      {status}
    </div>
  );
}
