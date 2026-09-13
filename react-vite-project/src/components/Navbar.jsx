import React from 'react';
import LanguageSwitcher from './LanguageSwitcher.jsx';

const Navbar = () => {
    return (
        <header className="fixed top-4 left-1/2 -translate-x-1/2 w-[92%] max-w-6xl z-50">
            <nav className="flex items-center justify-between px-6 py-3 rounded-2xl bg-white/30 backdrop-blur-md border border-white/40 shadow-lg shadow-purple-950/5">
                <h2 className="text-2xl font-extrabold text-kingfisher-daisy-900 tracking-tight">
                    T1Stagiaire
                </h2>
                <LanguageSwitcher />
            </nav>
        </header>
    );
};

export default Navbar;