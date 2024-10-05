import { getCarrinho } from "../../hooks/getDadosCarrinho";
import Cookies from "js-cookie";

interface ModalProps {
    closeModal(): void;
}

export function SeuCarrinho({ closeModal }: ModalProps) {
    const cartId = Cookies.get('cartId') || '';
    const { data: carrinho, isLoading, error } = getCarrinho(cartId);

    if (isLoading) {
        return <div>Carregando...</div>;
    }

    if (error) {
        return <div>Erro ao carregar o carrinho: {error.message}</div>;
    }

    if (!carrinho || !carrinho.itensPedido || carrinho.itensPedido.length === 0) {
        return <div>Seu carrinho está vazio</div>;
    }

    return (
        <div className="card mb-3">
            <div className="card-body">
                <h5 className="card-title">Carrinho: {carrinho.id}</h5>
                <button type="button" className="btn-close" aria-label="Close" onClick={closeModal}></button>
                <ul className="list-unstyled">
                    {carrinho.itensPedido.map((item) => (
                        <li key={item.id} className="mb-2">
                            <strong>Produto:</strong> {item.produto.nome} <br />
                            <strong>Quantidade:</strong> {item.quantidade} <br />
                            <strong>Preço Unitário:</strong> {item.produto.preco.toFixed(2)} <br />
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}
