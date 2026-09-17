const Discipline = {
    ComputerScience: "ComputerScience",
    Nursing: "Nursing",
    ElectricalEngineering: "ElectricalEngineering"
}

export const COMMON_INPUTS=[
    {id: 1, label: 'register.inputs.lastName.label', type: "text", placeholder: 'register.inputs.lastName.placeholder', name: "lastName"},
    {id: 2, label: 'register.inputs.firstName.label', type: "text", placeholder: 'register.inputs.firstName.placeholder', name: "firstName"},
    {id: 3, label: 'register.inputs.email.label', type: "email", placeholder: 'register.inputs.email.placeholder', name: "email"},
    {id: 6, label: 'register.inputs.password.label', type: "password", placeholder: "••••••••", name: "password"},
    {id: 7, label: 'register.inputs.confirmPassword.label', type: "password", placeholder: "••••••••", name: "passwordConfirmation"}
]

export const ROLE_CONFIG={
    student:{
        endpoint: '/register/student',
        extraInputs:[
            {id: 5, label: 'register.inputs.discipline.label', type: "select", placeholder: 'register.inputs.discipline.placeholder', name: "discipline", optionKeys: Object.values(Discipline)},
        ]
    },
    teacher:{
        endpoint: '/teacher/teacher',
        extraInputs:[]
    },
    employer:{
        endpoint: '/employer/employer',
        extraInputs:[]
    }
}