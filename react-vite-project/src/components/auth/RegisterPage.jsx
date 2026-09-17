import React, {useState} from 'react';
import {useTranslation} from 'react-i18next';
import FormInputs from "../FormInputs.jsx";
import axiosClient from "../../api/axiosClient.js";
import {COMMON_INPUTS, ROLE_CONFIG} from "./constants.js";


const RegisterPage = ({role}) => {
    const { t } = useTranslation();

    const DisciplineLabels = {
        ComputerScience: t('register.discipline.computerScience'),
        Nursing: t('register.discipline.nursing'),
        ElectricalEngineering: t('register.discipline.electricalEngineering')
    };

    const currentConfig = ROLE_CONFIG[role];

    const ACTIVE_INPUTS = [...COMMON_INPUTS, ...(currentConfig?.extraInputs || [])].map(input => {
        if (input.type === 'select' && input.optionKeys) {
            return {
                ...input,
                options: input.optionKeys.map(key => ({
                    value: key,
                    label: DisciplineLabels[key] ?? key
                }))
            };
        }
        return input;
    });

    const getInitialValues = () => {
        const initial = {};
        ACTIVE_INPUTS.forEach(input => {
            initial[input.name] = input.type === 'select' && input.options
                ? input.options[0].value
                : '';
        });
        return initial;
    };
    const [values, setValues] = useState(getInitialValues());

    const [error, setError] = useState('');
    const [disableButton, setDisableButton] = useState(false);
    const [simulationResponse, setSimulationResponse] = useState(null);

    const onChange = (e) => {
        const fieldName = e.target.name;
        setValues({...values, [fieldName]: e.target.value});
    }

    const isValid = () => {
        const hasEmptyFields = ACTIVE_INPUTS.some(input =>{
            const value = values[input.name];
            return !value || value.toString().trim() === '';
        });

        if (hasEmptyFields){
            setError('register.errors.allFieldsRequired');
            return false;
        }
        if(values.lastName.length < 1 || values.firstName.length < 1){
            setError('register.errors.nameMinLength');
            return false;
        }
        if (!validateEmail()){
            setError('register.errors.invalidEmail');
            return false;
        }
        if (values.password.length < 8){
            setError('register.errors.passwordMinLength');
            return false;
        }
        if (!motPasseCompatible()){
            setError('register.errors.passwordMismatch');
            return false;
        }
        return true;
    };
    const validateEmail = () => {
        const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
        return emailRegex.test(values.email);
    }
    const motPasseCompatible = () => {
        return values.password === values.passwordConfirmation;
    }

    const handleRegister = async (e) => {
        e.preventDefault();
        if (!isValid()) return;
        setError('');
        try{
            setDisableButton(true);
            const data = {...values}
            delete data.passwordConfirmation;
            console.log(data);
            const response = await axiosClient.post(ROLE_CONFIG.student.endpoint, data);
            setValues(getInitialValues());
            setSimulationResponse(JSON.stringify(response.data));
        }catch(e){
            setDisableButton(false);
            setError('register.errors.genericError');
        }finally {
            setDisableButton(false);
        }
    }

    return (
            <div>
                <div className="text-center">
                    <h1 className="title">{t('register.title')}</h1>
                    <p className="subtitle pb-6">{t('register.subtitle')}</p>
                </div>
                <form onSubmit={handleRegister}
                      className="forms-style">
                    {ACTIVE_INPUTS.map((input) => (
                       <FormInputs
                            key={input.id}
                            placeholder={t(input.placeholder)}
                            type={input.type}
                            value={values[input.name]}
                            onChange={onChange}
                            options={input.options}
                            name={input.name}
                            label={t(input.label)}
                      />
                    ))}
                    {error && <p className="error">{t(error)}</p>}
                    {simulationResponse && <p className="simulationResponse">{simulationResponse}</p>}
                    <div className="space-x-1">
                    <input type="checkbox" id="terms" name="terms" required />
                        <label htmlFor="terms">
                            {t("register.acceptTerms.label")}
                        </label>
                        <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank" rel="noopener noreferrer" className="text-blue-600 hover:underline">
                            {t("register.acceptTerms.linkText")}
                        </a>
                    </div>

                    <button type="submit"
                            className={`${disableButton ? 'btn-disabled' : 'btn-active'} btn`}
                            disabled={disableButton}>
                        {disableButton ? t('register.submitting') : t('register.submitButton')}
                    </button>
                </form>
            </div>

    );
};

export default RegisterPage;