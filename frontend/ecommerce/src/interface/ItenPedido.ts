import { DadosProdutos } from "./DadosProdutos"; 

export interface ItenPedido {
    id: string; // Unique identifier for the item
    produto: DadosProdutos; // Assuming you have a Produto interface
    quantidade: number;
    
}