import React from 'react';
import { useTranslation } from 'react-i18next';

const LanguageSwitcher = () => {
    const { i18n } = useTranslation();

    const currentLanguage = i18n.language;

    const handleLanguageChange = (e) => {
        const selectedLanguage = e.target.value;
        i18n.changeLanguage(selectedLanguage);
    };

    return (
        <div className="language-switcher">
            <select
                value={currentLanguage.slice(0, 2)}
                onChange={handleLanguageChange}
                className="language-select"
            >
                <option value="fr">Français</option>
                <option value="en">English</option>
            </select>
        </div>
    );
};

export default LanguageSwitcher;