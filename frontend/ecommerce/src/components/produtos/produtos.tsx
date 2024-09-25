import 'bootstrap/dist/css/bootstrap.min.css'; // Certifique-se de importar o Bootstrap no seu projeto
import './produtos.css'; // Importe seu CSS customizado se necessário
import { Decimal } from 'decimal.js';
import { useAuth } from '../../context/AuthProvider';
// export interface DadosProdutos {
//     codProd?: string,
//     nome: string,
//     preco: Decimal,
//     qtdEstoque: number,
//     categoria: string,
//     imagemUrl: string
// }

interface ProdutosProps {
    codProd?: string,
    nome: string,
    preco: Decimal,
    qtdEstoque: number,
    categoria: string,
    imagemUrl: string
}

export function Produtos({
    nome,
    preco,
    qtdEstoque,
    categoria,
    imagemUrl
}: ProdutosProps) {

    return (
        <div className="card mb-3">
            <div className="card-body">
                <img src={ imagemUrl }/>
                <h5 className="card-title">{nome}</h5>
                <ul className="list-unstyled">
                    <li className="mb-2"><strong>Preço:</strong> {preco.toString()}</li>
                    <li className="mb-2"><strong>Quantidade em Estoque:</strong> {qtdEstoque}</li>
                    <li className="mb-2"><strong>Categoria:</strong> {categoria}</li>
                </ul>
            </div>
        </div>
    );
}