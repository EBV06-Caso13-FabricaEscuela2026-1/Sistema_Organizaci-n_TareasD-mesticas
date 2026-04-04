interface InputProps {
  label: string;
  value: string;
  onChange: (value: string) => void;
  placeholder?: string;
  error?: string;
  maxLength?: number;
  showCounter?: boolean;
  required?: boolean;
  type?: string;
}

export function Input({
  label,
  value,
  onChange,
  placeholder,
  error,
  maxLength,
  showCounter,
  required,
  type = 'text'
}: InputProps) {
  return (
    <div className="w-full">
      <label className="block text-[#212121] font-medium mb-2">
        {label} {required && <span className="text-red-500">*</span>}
      </label>
      <input
        type={type}
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder={placeholder}
        maxLength={maxLength}
        className="w-full px-4 py-3 bg-[#F5F5F5] border border-[#E0E0E0] rounded-xl text-[#212121] placeholder:text-[#757575]"
      />
      <div className="flex justify-between items-start mt-1">
        {error && <p className="text-red-500 text-sm">{error}</p>}
        {showCounter && maxLength && (
          <p className="text-[#757575] text-sm ml-auto">{value.length}/{maxLength}</p>
        )}
      </div>
    </div>
  );
}
