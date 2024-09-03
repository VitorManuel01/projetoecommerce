import './clientes.css'; // Importe o arquivo CSS

interface ClientesProps {
    id?: number,
    nomeCliente: string,
    login: string,
    email: string,
    senha: string,
    CPF: string,
    sexo: string,
    dataNascimento: string | Date,
    CEP: string,
    bairro: string,
    telefone: string
}

export function Clientes({
    nomeCliente,
    login,
    email,
    senha,
    CPF,
    sexo,
    dataNascimento,
    CEP,
    bairro,
    telefone
}: ClientesProps) {
    const dataNascimentoFormatada = new Date(dataNascimento).toLocaleDateString();
    return (
        <div className="card-grid">
            <div className="cliente">
                <h2>Nome:</h2>
                <p>{nomeCliente}</p>
                <h3>Login:</h3>
                <p>{login}</p>
                <h3>Email:</h3>
                <p>{email}</p>          
                <h3>Senha:</h3>
                <p>{senha}</p>  
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
        </div>


    )
}