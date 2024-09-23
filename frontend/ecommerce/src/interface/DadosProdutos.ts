import { Decimal } from 'decimal.js';

export interface DadosProdutos {
    codProd?: string,
    nome: string,
    preco: Decimal,
    qtdEstoque: number,
    categoria: string,
    imagemUrl: string
}