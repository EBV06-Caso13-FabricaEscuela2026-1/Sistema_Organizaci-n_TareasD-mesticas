interface ButtonProps {
  children: React.ReactNode;
  onClick?: () => void;
  variant?: 'primary' | 'secondary';
  disabled?: boolean;
  type?: 'button' | 'submit';
}

export function Button({ children, onClick, variant = 'primary', disabled, type = 'button' }: ButtonProps) {
  if (variant === 'secondary') {
    return (
      <button
        type={type}
        onClick={onClick}
        disabled={disabled}
        className="w-full py-3 text-[#757575] disabled:opacity-50"
      >
        {children}
      </button>
    );
  }

  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      className="w-full h-[52px] bg-[#00BFA5] text-white rounded-xl font-medium disabled:opacity-50"
    >
      {children}
    </button>
  );
}
