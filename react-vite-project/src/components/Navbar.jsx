import React from 'react';
import LanguageSwitcher from './LanguageSwitcher.jsx';

const Navbar = () => {
    return (
        <nav style={{ display: 'flex', justifyContent: 'space-between', padding: '1rem' }}>
            <h2>T1Stagiaire</h2>
            <LanguageSwitcher />
        </nav>
    );
};

export default Navbar;