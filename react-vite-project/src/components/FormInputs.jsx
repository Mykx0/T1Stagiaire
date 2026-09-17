import React, {useState} from 'react';

const FormInputs = ({placeholder, type, value, onChange, name, label, options}) => {
    const [showPassword, setShowPassword] = useState(false);
    const currentTypePassword = type === 'password' && showPassword ? 'text' : type;
    const [isOpen, setIsOpen] = useState(false);
    const isSelectType = type === 'select';

    return (
        <div className={"formInputs"}>
            <label className="labels">
                {label}
            </label>
            <div>
                {isSelectType ? (
                // /*liste des disciplines*/
                    <div className="relative w-full text-left">
                        <button
                            type="button"
                            onClick={() => setIsOpen(!isOpen)}
                            className="inputs px-3 py-2 ease shadow-sm outline-none transition-all w-full flex items-center justify-between cursor-pointer capitalize text-left"
                        >
                            <span>{(value ? String(value) : placeholder || 'Sélectionner...').toLowerCase().replace(/_/g, ' ')}</span>
                            <svg className={`size-4 text-kingfisher-daisy-600 transition-transform duration-200 ${isOpen ? 'rotate-180' : ''}`}>
                                <use href={"/sprite.svg#arrowDown"}/>
                            </svg>
                        </button>

                        {isOpen && (
                            <>
                                <div className="fixed inset-0 z-10" onClick={() => setIsOpen(false)} />

                                <ul className="absolute left-0 mt-1 w-full bg-white border-gray-200 border rounded-md shadow-lg
                            overflow-y-auto z-20 p-1.5 animate-in fade-in duration-100
                             scrollbar-thin scrollbar-thumb-kingfisher-daisy-600">
                                    {options?.map((option) => (
                                        <li
                                            key={option}
                                            onClick={() => {
                                                onChange({ target: { name, value: option } } );
                                                setIsOpen(false);
                                            }}
                                            className={`px-3 py-2 text-sm rounded-lg cursor-pointer capitalize transition-colors select-none
                                ${value === option
                                                ? 'bg-kingfisher-daisy-200 text-kingfisher-daisy-600 font-bold'
                                                : 'text-slate-700 hover:bg-kingfisher-daisy-200 hover:text-kingfisher-daisy-600'
                                            }`}
                                        >
                                            {option.toLowerCase()}
                                        </li>
                                    ))}
                                </ul>
                            </>
                        )}
                    </div>
                ):(
                    <>
                        <input type={currentTypePassword} placeholder={placeholder}
                               className="inputs"
                               name={name}
                               value={value}
                               onChange={onChange}
                        />
                        {type === 'password' && (
                            <button type="button" onClick={() => setShowPassword(!showPassword)}
                            className={"btn-formEyes"}
                            >
                                {showPassword ?
                                    (<svg className={"size-6"}><use href={"/sprite.svg#eyeClose"}/> </svg>)
                                    : (<svg className={"size-6"}><use href={"/sprite.svg#eyeOpen"}/></svg>)}
                            </button>
                        )}
                    </>
                )}
            </div>

        </div>
    );
};

export default FormInputs;