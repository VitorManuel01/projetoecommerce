import axios, { AxiosPromise } from "axios"
import { dadosClientes } from "../interface/dadosClientes";
import { useQuery } from "@tanstack/react-query";

const API_URL = 'http://127.0.0.1:8080'

const fetchData = async (): AxiosPromise<dadosClientes[]> => { 
    const response = axios.get(API_URL + "/cliente" );
    return response;
}


export function useDadosClientes(){
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['dados-cliente'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data
    }
}