import React, {useState} from 'react';

const FormInputs = ({placeholder, type, value, onChange, name, label}) => {
    const [showPassword, setShowPassword] = useState(false);
    const currentTypePassword = type === 'password' && showPassword ? 'text' : type;

    return (
        <div className={"formInputs"}>
            <label className="labels">
                {label}
            </label>
            <div>
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
            </div>
        </div>
    );
};

export default FormInputs;