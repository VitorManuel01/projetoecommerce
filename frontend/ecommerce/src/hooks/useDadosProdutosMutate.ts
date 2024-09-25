import axios, { AxiosPromise } from "axios"
import { DadosProdutos } from "../interface/DadosProdutos";
import { useMutation, useQueryClient } from "@tanstack/react-query";



const postData = async (data: DadosProdutos): AxiosPromise<any> => {
    // Obtenha o token do armazenamento local ou do contexto
    const token = localStorage.getItem('token'); // ou use AuthContext para pegar o token

    const response = axios.post("/produto", data, {
        headers: {
            Authorization: `Bearer ${token}`, // Adiciona o token no cabeçalho
        },
    });
    return response;
}


export function useDadosProdutosMutate(){
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () =>{
            queryClient.invalidateQueries( ['dados-produto'] as any)
        }
    })

    return mutate;
}