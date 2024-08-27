import axios, { AxiosPromise } from "axios"
import { DadosClientes } from "../interface/DadosClientes";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const API_URL = 'http://127.0.0.1:8080'

const postData = async (data: DadosClientes): AxiosPromise<any> => { 
    const response = axios.post(API_URL + "/cliente", data);
    return response;
}


export function useDadosClientesMutate(){
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () =>{
            queryClient.invalidateQueries( ['dados-cliente'] as any)
        }
    })

    return mutate;
}