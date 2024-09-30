import axios, { AxiosPromise } from "axios"
import { DadosProdutos } from "../interface/DadosProdutos";
import { useMutation, useQueryClient } from "@tanstack/react-query";



const putDados = async (codProd: string, data:DadosProdutos): Promise<AxiosPromise<any>> => {
    // Obtenha o token do armazenamento local ou do contexto
    const token = localStorage.getItem('token'); // ou use AuthContext para pegar o token

    // Faz a requisição PUT com o ID do produto na URL
    const response = await axios.put(`/produto/${codProd}`,data, {
        headers: {
            Authorization: `Bearer ${token}`, // Adiciona o token no cabeçalho
        },
    });
    return response;
}


export function updateDadosProdutos() {
    const queryClient = useQueryClient();

    const mutate = useMutation({
        mutationFn: ({ codProd, data }: { codProd: string, data: DadosProdutos }) => putDados(codProd, data),
        retry: 2,
        onSuccess: () => {
            // Invalida a query de 'dados-produto' para que seja atualizada
            queryClient.invalidateQueries(['dados-produto'] as any);
        },
    });

    return mutate;
}