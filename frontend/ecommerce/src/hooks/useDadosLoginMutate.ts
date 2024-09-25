import axios, { AxiosPromise } from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { LoginData } from "../interface/LoginData";
import { useAuth } from "../context/AuthProvider";
import { jwtDecode } from 'jwt-decode';
import { JwtDadosUsuario } from "../interface/JwtDadosUsuario";

// Função para realizar o POST de login
const postLogin = async (data: LoginData): AxiosPromise<any> => {
    try {
        const response = await axios.post("/auth/login", data, {
            headers: {
                "Content-Type": "application/json",
            },
            withCredentials: true,
        });
        console.log("Dados pra tentar login", data);
        console.log("Resposta", response);
        return response.data; // Retorne apenas os dados
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.error("Error response:", error.response?.data);
            console.error("Error status:", error.response?.status);
            console.error("Error headers:", error.response?.headers);
            throw error;
        } else {
            console.error("Unexpected error:", error);
            throw error;
        }
    }
};

export const useDadosLoginMutate = () => {
    const queryClient = useQueryClient();
    const { login } = useAuth();

    const mutate = useMutation({
        mutationFn: postLogin,
        retry: 2,
        onSuccess: (data) => {
            // @ts-ignore
            const token = data?.token
            
            console.log("O token é", token)
            const tokenDecodificado: JwtDadosUsuario = jwtDecode(token)
            console.log(tokenDecodificado)
            
            if (token) {
                login(token);
            }else{
                console.log("Não foi possível capturar o token")
            }

            // Invalida as queries relacionadas ao usuário
            console.log("Sucesso ao realizar login");
            queryClient.invalidateQueries(['dados-login'] as any);
        }, onError: (error) => {
            console.error("Erro ao fazer login", error)
        }
    });

    return mutate;
}