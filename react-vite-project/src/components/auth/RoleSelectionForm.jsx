import React, { useState } from 'react';
import RegisterPage from "./RegisterPage.jsx";
import {useTranslation} from "react-i18next";
// import TeacherForm from './TeacherForm';
// import EmployerForm from './EmployerForm';

const ROLES = {
    STUDENT: 'student',
    TEACHER: 'teacher',
    EMPLOYER: 'employer',
};

export default function RoleSelectionForm() {
    const { t } = useTranslation();

    const [selectedRole, setSelectedRole] = useState(null);

    const roleCards = [
        {
            id: ROLES.STUDENT,
            title: t('roles.student.title'),
            description: t('roles.student.description'),
            iconId: 'student',
        },
        {
            id: ROLES.TEACHER,
            title: t('roles.teacher.title'),
            description: t('roles.teacher.description'),
            iconId: 'teacher',
        },
        {
            id: ROLES.EMPLOYER,
            title: t('roles.employer.title'),
            description: t('roles.employer.description'),
            iconId: 'employer',
        },
    ];

    return (
        <div className="max-w-4xl mx-auto p-6 pt-20 min-h-screen flex flex-col justify-center items-center">
            <div className="w-full">

                {!selectedRole && (
                    <>
                        <div className="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm mb-8 text-center max-w-lg mx-auto">
                            <h2 className="text-2xl font-bold text-gray-900 mb-2">
                                {t('roleSelection.title')}
                            </h2>
                            <p className="text-gray-600 text-sm">
                                {t('roleSelection.subtitle')}
                            </p>
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8 items-center">
                            {roleCards.map((card) => (
                                <button
                                    key={card.id}
                                    type="button"
                                    onClick={() => setSelectedRole(card.id)}
                                    className="group flex flex-col items-center justify-center p-6 aspect-square rounded-2xl border-2 transition-all duration-200 cursor-pointer text-center border-gray-200 bg-white text-gray-600 hover:border-kingfisher-daisy-800 hover:bg-kingfisher-daisy-50 hover:text-kingfisher-daisy-800 hover:shadow-md hover:scale-105"
                                >
                                    <div className="flex items-center justify-center p-4 rounded-full mb-4 transition-colors duration-200 bg-gray-100 text-kingfisher-daisy-800 group-hover:bg-kingfisher-daisy-800 group-hover:text-white">
                                        <svg className="w-10 h-10">
                                            <use href={`/sprite.svg#${card.iconId}`} />
                                        </svg>
                                    </div>

                                    <span className="text-xl font-bold mb-1">{card.title}</span>
                                    <span className="text-xs text-gray-500 transition-colors duration-200 group-hover:text-kingfisher-daisy-800">
                    {card.description}
                  </span>
                                </button>
                            ))}
                        </div>
                    </>
                )}

                {selectedRole && (
                    <div className="bg-white p-6 rounded-2xl border border-gray-200 shadow-sm w-full max-w-xl mx-auto">
                        <button
                            type="button"
                            onClick={() => setSelectedRole(null)}
                            className="mb-6 text-sm text-kingfisher-daisy-800 hover:underline flex items-center gap-1 font-medium"
                        >
                            ← {t('roleSelection.backButton')}
                        </button>

                        {selectedRole === ROLES.STUDENT && <RegisterPage />}
                        {selectedRole === ROLES.TEACHER && <TeacherForm />}
                        {selectedRole === ROLES.EMPLOYER && <EmployerForm />}
                    </div>
                )}

            </div>
        </div>
    );
}