import React, {useState} from 'react';
import { useTranslation } from 'react-i18next';
import FormInputs from "../FormInputs.jsx";


const RegisterPage = () => {
    const { t } = useTranslation();

    const INPUTS = [
        {id: 1, label: t('register.inputs.lastName.label'), type: "text", placeholder: t('register.inputs.lastName.placeholder'), name: "nom"},
        {id: 2, label: t('register.inputs.firstName.label'), type: "text", placeholder: t('register.inputs.firstName.placeholder'), name: "prenom"},
        {id: 3, label: t('register.inputs.email.label'), type: "email", placeholder: t('register.inputs.email.placeholder'), name: "courriel"},
        {id: 4, label: t('register.inputs.password.label'), type: "password", placeholder: "•••••", name: "motPasse"},
        {id: 5, label: t('register.inputs.confirmPassword.label'), type: "password", placeholder: "•••••", name: "motPasseConfirmation"}
    ];

    const [values, setValues] = useState({
        nom: '',
        prenom: '',
        courriel: '',
        motPasse: '',
        motPasseConfirmation: ''
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
        if(values.nom.length < 1 || values.prenom.length < 1){
            setError('register.errors.nameMinLength');
            return false;
        }
        if (!validateEmail()){
            setError('register.errors.invalidEmail');
            return false;
        }
        if (values.motPasse.length < 8){
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
        return values.nom !== '' && values.prenom !== '' && values.matricule !== '' && values.courriel !== '' && values.motPasse !== '' && values.motPasseConfirmation !== '';
    }
    const validateEmail = () => {
        const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
        return emailRegex.test(values.courriel);
    }
    const motPasseCompatible = () => {
        return values.motPasse === values.motPasseConfirmation;
    }

    const handleRegister = async (e) => {
        e.preventDefault();
        if (!isValid()) return;
        setError('');
        try{
            setDisableButton(true);
            const data = {
                nom: values.nom,
                prenom: values.prenom,
                courriel: values.courriel,
                motPasse: values.motPasse
            };
            await new Promise(resolve => setTimeout(resolve, 2000));
            console.log(data)
            setValues({
                nom: '',
                prenom: '',
                courriel: '',
                motPasse: '',
                motPasseConfirmation: ''
            });
            setSimulationResponse( `${t('register.successMessage')}\n${JSON.stringify(data)}`);
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