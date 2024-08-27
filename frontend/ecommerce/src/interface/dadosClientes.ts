export interface DadosClientes {
    id?: number,
    codCliente?: number,
    nomeCliente: string,
    CPF: string,
    sexo: string,
    dataNascimento: string | Date,
    CEP: string,
    bairro: string,
    telefone: string
}