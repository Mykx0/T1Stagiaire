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
                    className={"h-full absolute right-3 top-1/2 -translate-y-1/2 font-semibold"}
                    >
                        {showPassword ? 'Hide' : 'Show'}
                    </button>
                )}
            </div>
        </div>
    );
};

export default FormInputs;