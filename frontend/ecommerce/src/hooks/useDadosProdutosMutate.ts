import axios, { AxiosPromise } from "axios"
import { DadosProdutos } from "../interface/DadosProdutos";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const API_URL = 'http://127.0.0.1:8080'

const postData = async (data: DadosProdutos): AxiosPromise<any> => { 
    const response = axios.post(API_URL + "/produto", data);
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