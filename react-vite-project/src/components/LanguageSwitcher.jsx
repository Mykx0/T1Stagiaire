import React from 'react';
import { useTranslation } from 'react-i18next';

const LanguageSwitcher = () => {
    const { i18n } = useTranslation();

    const currentLanguage = i18n.language ? i18n.language.slice(0, 2) : 'fr';

    const handleLanguageChange = (e) => {
        const selectedLanguage = e.target.value;
        i18n.changeLanguage(selectedLanguage);
    };

    return (
        <div className="relative flex items-center">
            <svg className={"w-4 h-4 text-kingfisher-daisy-800 absolute left-3 pointer-events-none z-10"}><use href={"/sprite.svg#monde"}/></svg>

            <select
                value={currentLanguage}
                onChange={handleLanguageChange}
                className="pl-9 pr-8 py-1.5 text-xs font-bold text-kingfisher-daisy-900
                           bg-white/40 hover:bg-white/60
                           backdrop-blur-md border border-white/60 rounded-xl shadow-sm
                           hover:shadow-md hover:border-white
                           focus:outline-none focus:ring-2 focus:ring-kingfisher-daisy-400
                           transition-all duration-200 ease-in-out cursor-pointer appearance-none"
            >
                <option value="fr" className="bg-white text-gray-800 font-medium">Français</option>
                <option value="en" className="bg-white text-gray-800 font-medium">English</option>
            </select>

            <div className="absolute right-2.5 pointer-events-none text-kingfisher-daisy-800 text-[10px]">
                ▼
            </div>
        </div>
    );
};

export default LanguageSwitcher;