import { fetchCarrinho } from "./useDadosCarrinho";
import { Carrinho } from "../interface/Carrinho";
import { useQuery } from "@tanstack/react-query";

export function getCarrinho(cartId: string) {
    return useQuery<Carrinho, Error>({
        queryKey: ['carrinho', cartId],
        queryFn: () => {
            if (!cartId) return Promise.reject("No cart ID available.");
            return fetchCarrinho(cartId);
        },
        enabled: !!cartId, // Only fetch if cartId exists
        retry: 0,
    });
}