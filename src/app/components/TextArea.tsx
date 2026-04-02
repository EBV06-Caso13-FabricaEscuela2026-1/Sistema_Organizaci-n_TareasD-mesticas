interface TextAreaProps {
  label: string;
  value: string;
  onChange: (value: string) => void;
  placeholder?: string;
  error?: string;
  maxWords?: number;
  required?: boolean;
}

export function TextArea({
  label,
  value,
  onChange,
  placeholder,
  error,
  maxWords,
  required
}: TextAreaProps) {
  const wordCount = value.trim() ? value.trim().split(/\s+/).length : 0;

  return (
    <div className="w-full">
      <label className="block text-[#212121] font-medium mb-2">
        {label} {required && <span className="text-red-500">*</span>}
      </label>
      <textarea
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder={placeholder}
        rows={4}
        className="w-full px-4 py-3 bg-[#F5F5F5] border border-[#E0E0E0] rounded-xl text-[#212121] placeholder:text-[#757575] resize-none"
      />
      <div className="flex justify-between items-start mt-1">
        {error && <p className="text-red-500 text-sm">{error}</p>}
        {maxWords && (
          <p className="text-[#757575] text-sm ml-auto">{wordCount}/{maxWords} palabras</p>
        )}
      </div>
    </div>
  );
}
