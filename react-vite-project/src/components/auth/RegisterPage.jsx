import React, {useState} from 'react';
import FormInputs from "../FormInputs.jsx";

const INPUTS = [
    {id: 1, label: "Nom", type: "text", placeholder: "Votre nom", name: "nom"},
    {id: 2, label: "Prénom", type: "text", placeholder: "Votre prénom", name: "prenom"},
    {id: 3, label: "Numéro de matricule", type: "text", placeholder: "Votre matricule", name: "matricule"},
    {id: 4, label:"Courriel", type: "email", placeholder: "vous@exemple.com", name: "courriel"},
    {id: 5, label: "Mot de passe", type: "password", placeholder: "•••••", name: "motPasse"},
    {id: 6, label: "Confirmer mot de passe", type: "password", placeholder: "•••••", name: "motPasseConfirmation"}
];
const RegisterPage = () => {
    const [values, setValues] = useState({
        nom: '',
        prenom: '',
        matricule: '',
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
            setError('Veuillez remplir tous les champs');
            return false;
        }
        if(values.nom.length < 1 || values.prenom.length < 1){
            setError('Le nom et le prénom doivent contenir au moins 1 caractère');
            return false;
        }
        if (!validateEmail()){
            setError('Veuillez entrer un courriel valide');
            return false;
        }
        if (values.motPasse.length < 8){
            setError('Le mot de passe doit contenir au moins 8 caractères');
            return false;
        }
        if (!motPasseCompatible()){
            setError('Les mots de passe ne correspondent pas');
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
                matricule: values.matricule,
                courriel: values.courriel,
                motPasse: values.motPasse
            };
            // await new Promise(resolve => setTimeout(resolve, 2000));
            console.log(data)
            setValues({
                nom: '',
                prenom: '',
                matricule: '',
                courriel: '',
                motPasse: '',
                motPasseConfirmation: ''
            });
            setSimulationResponse( 'Inscription réussie ! Vous pouvez maintenant vous connecter. \nDonnées envoyées : ' + JSON.stringify(data));
        }catch(e){
            setDisableButton(false);
            setError('Une erreur est survenue lors de l\'inscription');
        }finally {
            setDisableButton(false);
        }
    }


    return (
        <div className="form-body">
            <div className={"form-body-enfant"}>
                <div>
                    <h1 className={"text-3xl"}>Créer un compte</h1>
                    <p className="mt-2 text-sm">Écrivez vos informations pour s'inscrire</p>
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
                    {error && <p className="text-red-500">{error}</p>}
                    {simulationResponse && <p className="text-green-500">{simulationResponse}</p>}
                    <button type="submit" className="text-black p-2 rounded-md w-full bg-chrome-white-300"
                            disabled={disableButton}>
                        {disableButton ? 'Inscription en cours...' : 'S\'inscrire'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default RegisterPage;