import axios, { AxiosPromise } from "axios"
import { DadosProdutos } from "../interface/DadosProdutos"
import { useQuery } from "@tanstack/react-query";



const fetchData = async (): AxiosPromise<DadosProdutos[]> => { 
    const response = axios.get("/produto");
    return response;
}


export function useDadosProdutos(){
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['dados-produto'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data
    }
}