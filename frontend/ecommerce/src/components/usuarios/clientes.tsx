import './clientes.css'; // Importe o arquivo CSS

interface ClientesProps {
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

export function Clientes({
    codCliente, 
    nomeCliente, 
    CPF, 
    sexo, 
    dataNascimento, 
    CEP, 
    bairro, 
    telefone
} : ClientesProps) {
    const dataNascimentoFormatada = new Date(dataNascimento).toLocaleDateString();
    return (

        <div className="cadastro">
            <h2>Nome:</h2>
            <p>{nomeCliente}</p>
            <h2>Código:</h2>
            <p>{codCliente}</p>
            <h3>CPF:</h3>
            <p>{CPF}</p>
            <h3>Sexo:</h3>
            <p>{sexo}</p>
            <h3>Data de Nascimento:</h3>
            <p>{dataNascimentoFormatada}</p>
            <h3>CEP:</h3>
            <p>{CEP}</p>
            <h3>Bairro:</h3>
            <p>{bairro}</p>
            <h3>Telefone:</h3>
            <p>{telefone}</p>
        </div>

    )
}