import React, {useState} from 'react';
import { useTranslation } from 'react-i18next';
import FormInputs from "../FormInputs.jsx";
import axiosClient from "../../api/axiosClient.js";


const RegisterPage = () => {
    const { t } = useTranslation();

    const Discipline = {
        ComputerScience: t('register.discipline.computerScience'),
        Nursing: t("register.discipline.nursing"),
        ElectricalEngineering: t("register.discipline.electricalEngineering")
    }

    const INPUTS = [
        {id: 1, label: t('register.inputs.lastName.label'), type: "text", placeholder: t('register.inputs.lastName.placeholder'), name: "last_name"},
        {id: 2, label: t('register.inputs.firstName.label'), type: "text", placeholder: t('register.inputs.firstName.placeholder'), name: "first_name"},
        {id: 3, label: t('register.inputs.email.label'), type: "email", placeholder: t('register.inputs.email.placeholder'), name: "email"},
        {id: 5, label: t('register.inputs.discipline.label'), type: "select", placeholder: t('register.inputs.discipline.placeholder'), name: "discipline", options: Object.values(Discipline)},
        {id: 6, label: t('register.inputs.password.label'), type: "password", placeholder: "•••••", name: "password"},
        {id: 7, label: t('register.inputs.confirmPassword.label'), type: "password", placeholder: "•••••", name: "passwordConfirmation"}
    ];

    const [values, setValues] = useState({
        last_name: '',
        first_name: '',
        email: '',
        discipline: Discipline.ComputerScience,
        password: '',
        passwordConfirmation: ''
    });

    const [error, setError] = useState('');
    const [disableButton, setDisableButton] = useState(false);
    const [simulationResponse, setSimulationResponse] = useState(null);

    const onChange = (e) => {
        const fieldName = e.target.name;
        setValues({...values, [fieldName]: e.target.value});
    }

    const isValid = () => {
        if (!champsComplets()) {
            setError('register.errors.allFieldsRequired');
            return false;
        }
        if(values.last_name.length < 1 || values.first_name.length < 1){
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
    const champsComplets = () => {
        return values.last_name !== '' && values.first_name !== '' && values.email !== '' && values.discipline !== '' && values.password !== '' && values.passwordConfirmation !== '';
    }
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
            const data = {
                last_name: values.last_name,
                first_name: values.first_name,
                email: values.email,
                discipline: values.discipline,
                password: values.password
            };
            const response = await axiosClient.post('/register/student', data);
            console.log(data)
            setValues({
                last_name: '',
                first_name: '',
                email: '',
                discipline: Discipline.ComputerScience,
                password: '',
                passwordConfirmation: ''
            });
            setSimulationResponse( await axiosClient.get(`/${response.data.id}`));
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
                    {INPUTS.map((input)=>(
                       <FormInputs
                            key={input.id}
                            placeholder={input.placeholder}
                            type={input.type}
                            value={values[input.name]}
                            onChange={onChange}
                            options={input.options}
                            name={input.name}
                            label={input.label}
                      />
                    ))}
                    {error && <p className="error">{t(error)}</p>}
                    {simulationResponse && <p className="simulationResponse">{simulationResponse}</p>}
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