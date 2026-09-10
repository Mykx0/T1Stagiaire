import React from 'react';

const Inscription = () => {
    const form = [
        {
            label: "Nom", type: "text", placeholder: "Nom", name: "nom", required: true
        },
        {
            label: "Prénom", type: "text", placeholder: "Prénom", name: "prenom", required: true
        },
        {
            label:"Courriel", type: "email", placeholder: "Courriel", name: "courriel", required: true
        },
        {
            label: "Mot de passe", type: "password", placeholder: "Mot de passe", name: "motDePasse", required: true
        },
        {
            label: "Confirmer mot de passe", type: "password", placeholder: "Confirmer le mot de passe", name: "confirmerMotDePasse", required: true
        }

    ]
    return (
        <div className="form-body">
            <h1>Inscription</h1>
            <form className="d-flex flex-column space-y-1 bg-chrome-white-100">
                {form.map((input, index) => (
                    <div key={index} className="d-flex flex-column space-x-2">
                        <label htmlFor={input.name}>{input.label}</label>
                        <input type={input.type} placeholder={input.placeholder} name={input.name} required={input.required} />
                    </div>))}
                <button type="submit" className="bg-chrome-white-50 text-white p-2 rounded-md">S'inscrire</button>
            </form>
        </div>
    );
};

export default Inscription;