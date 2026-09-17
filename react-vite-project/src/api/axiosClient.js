import axios from 'axios';

const axiosClient = axios.create({
    baseURL: "http://localhost:8080/api",
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
});

axiosClient.interceptors.request.use(
    (config)=>{
        const token = localStorage.getItem('auth_token');

        if (token && config.headers) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error)=>{
        return Promise.reject(error);
    }
);

axiosClient.interceptors.response.use(
    (response)=> {
        return response;
    },
    (error)=>{
        const status = error.response && error.response.status;
        if (status === 401|| status === 403) {
            // Rediriger vers la page de connexion (plus tard)
            localStorage.removeItem('auth_token');
        }
        return Promise.reject(error);
    }
);

export default axiosClient;