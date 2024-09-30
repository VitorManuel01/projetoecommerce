import axios, { AxiosPromise } from "axios"
import { DadosProdutos } from "../interface/DadosProdutos";
import { useMutation, useQueryClient } from "@tanstack/react-query";



const deleteDados = async (codProd: string): Promise<AxiosPromise<any>> => {
    // Obtenha o token do armazenamento local ou do contexto
    const token = localStorage.getItem('token'); // ou use AuthContext para pegar o token

    // Faz a requisição DELETE com o ID do produto na URL
    const response = await axios.delete(`/produto/${codProd}`, {
        headers: {
            Authorization: `Bearer ${token}`, // Adiciona o token no cabeçalho
        },
    });
    return response;
}


export function deleteDadosProdutos() {
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: deleteDados,
        onSuccess: () => {
            queryClient.invalidateQueries(['dados-produto'] as any); // Invalida a consulta para atualizar a lista de produtos
        },
        onError: (error) => {
            console.error("Erro ao deletar o produto:", error);
        }
    });

    return mutate;
}