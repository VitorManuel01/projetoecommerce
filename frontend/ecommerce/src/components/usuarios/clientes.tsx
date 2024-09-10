import 'bootstrap/dist/css/bootstrap.min.css'; // Certifique-se de importar o Bootstrap no seu projeto
import './clientes.css'; // Importe seu CSS customizado se necessário

interface ClientesProps {
    id?: number;
    nomeCliente: string;
    login: string;
    email: string;
    senha: string;
    CPF: string;
    sexo: string;
    dataNascimento: string | Date;
    CEP: string;
    bairro: string;
    telefone: string;
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
        <div className="card mb-3">
            <div className="card-body">
                <h5 className="card-title">{nomeCliente}</h5>
                <ul className="list-unstyled">
                    <li className="mb-2"><strong>Login:</strong> {login}</li>
                    <li className="mb-2"><strong>Email:</strong> {email}</li>
                    <li className="mb-2"><strong>Senha:</strong> {senha}</li>
                    <li className="mb-2"><strong>CPF:</strong> {CPF}</li>
                    <li className="mb-2"><strong>Sexo:</strong> {sexo}</li>
                    <li className="mb-2"><strong>Data de Nascimento:</strong> {dataNascimentoFormatada}</li>
                    <li className="mb-2"><strong>CEP:</strong> {CEP}</li>
                    <li className="mb-2"><strong>Bairro:</strong> {bairro}</li>
                    <li className="mb-2"><strong>Telefone:</strong> {telefone}</li>
                </ul>
            </div>
        </div>
    );
}