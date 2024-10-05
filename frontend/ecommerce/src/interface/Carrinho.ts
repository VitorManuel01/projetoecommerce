import { ItenPedido } from "./ItenPedido"; // Adjust import as per your structure

export interface Carrinho {
    id: string;
    itensPedido: ItenPedido[];
}