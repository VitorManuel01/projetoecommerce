// axiosConfig.ts
import axios from 'axios';

axios.defaults.baseURL = 'http://127.0.0.1:8080'; // Defina a URL base

axios.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default axios;