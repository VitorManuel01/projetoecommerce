import axios, { AxiosPromise } from "axios"
import {useMutation, useQueryClient } from "@tanstack/react-query";
import { ComprarProdutoRequestDTO } from "../interface/ComprarProdutoRequestDTO"; 
import { useEffect, useState } from "react";
import Cookies from "js-cookie";
import { AxiosError } from 'axios';

const isAxiosError = (error: unknown): error is AxiosError => {
    return (error as AxiosError).isAxiosError !== undefined;
};

const createCarrinho = async (): Promise<string> => {
    try {
        const response = await axios.post("/carrinho");
        console.log("Carrinhp:", response.data);
        Cookies.set("cartId", response.data.id, { expires: 1 });
        const respostaTrimada = response.data.id.trim();
        return respostaTrimada; 
    } catch (error) {
        if (isAxiosError(error)) {
            console.error("Erro ao criar carrinho:", error.response?.data || error.message);
        } else {
            console.error("Erro inesperado:", error);
        }
        throw error; 
    }
};

export {createCarrinho};

const fetchCarrinho = async (cartId: string) => {
    try {
        const response = await axios.get(`/carrinho/${cartId}`);
        console.log("Carrinho pego:", response.data);
        return response.data;
    } catch (error) {
        if (isAxiosError(error)) {
            console.error("Erro ao pegar carrinho:", error.response?.data || error.message);
        } else {
            console.error("Unexpected error:", error);
        }
        throw error; 
    }
};

export {fetchCarrinho};

const postCarrinho = async (data: ComprarProdutoRequestDTO): AxiosPromise<string> => {
    const cartId = Cookies.get("cartId")?.trim();
    if (!cartId) {
        throw new Error("Carrinho não encontrado.");
    }
    
    console.log("Post é:", `/carrinho/${cartId}/comprar`, " Dados:", data);
    
    const response = await axios.post(`/carrinho/${cartId}/comprar`, data);
    console.log("Resposta do post de add item:", response.data); 
    return response.data; 
};


export function useCarrinho() {
    const queryClient = useQueryClient();
    const [cartId, setCartId] = useState<string | null>(Cookies.get("cartId") || null);

    useEffect(() => {
        const initializeCart = async () => {
            if (!cartId) {
                const id = (await createCarrinho()).trim(); 
                setCartId(id); 
            }
        };
        initializeCart();
    }, [cartId]);

    const mutate = useMutation({
        mutationFn: async (data: ComprarProdutoRequestDTO) => {
            const id = Cookies.get("cartId")?.trim(); 
            if (!id) {
                throw new Error("Carrinho não encontrado."); 
            }
            console.log("request :", `/carrinho/${id}/comprar`, " + dados:", data);
            return postCarrinho(data);
        },
        onSuccess: () => {
            queryClient.invalidateQueries(['dados-carrinho', cartId] as any);
        },
        onError: (error) => {
            console.error("Erro ao adicionar item ao carrinho:", error); 
        },
        retry:0
    });
    
    return {
        addItem: mutate.mutate
    };
}
