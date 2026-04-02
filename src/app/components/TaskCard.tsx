interface TaskCardProps {
  taskName: string;
  onClick: () => void;
}

export function TaskCard({ taskName, onClick }: TaskCardProps) {
  return (
    <button
      onClick={onClick}
      className="w-full bg-[#B2DFDB] px-4 py-4 rounded-xl text-left text-[#212121] font-medium hover:bg-[#80CBC4] transition-colors"
    >
      {taskName}
    </button>
  );
}
