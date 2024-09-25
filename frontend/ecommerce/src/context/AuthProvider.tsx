import React, { createContext, useContext, useState } from 'react';
import { AuthContextType } from "../interface/AuthContextType"
import { JwtDadosUsuario } from '../interface/JwtDadosUsuario';
import { jwtDecode } from 'jwt-decode';

// Cria o contexto
const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [funcao, setFuncao] = useState("")
    const login = (token: string) => {
        localStorage.setItem('token', token);
        const tokenDecodificado: JwtDadosUsuario = jwtDecode(token)
        setIsAuthenticated(true);
        console.log(tokenDecodificado.funcao)
        setFuncao(tokenDecodificado.funcao)
    };

    const logout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
        setFuncao("")
        //console.log("Logout realizado com sucesso")
    };

    return (
        
        <AuthContext.Provider value={{ isAuthenticated,funcao, login, logout }}>
            {children}
            
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};