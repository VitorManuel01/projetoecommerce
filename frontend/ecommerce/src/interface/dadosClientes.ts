export interface DadosClientes {
    id?: string; 
    login: string;
    email: string;
    senha: string;
    nomeCliente: string;
    CPF: string;
    sexo: string;
    dataNascimento: string | Date; 
    CEP: string;
    bairro: string;
    telefone: string;
}