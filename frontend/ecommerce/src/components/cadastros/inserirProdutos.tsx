import { useEffect, useState } from 'react';
import { useDadosProdutosMutate } from '../../hooks/useDadosProdutosMutate';
import { DadosProdutos } from '../../interface/DadosProdutos';
import { Decimal } from 'decimal.js';
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa o CSS do Bootstrap
import './cadastrarCliente.css'; // Importe seu CSS customizado se necessário

interface InputProps {
    label: string;
    value: string | number | Decimal;
    updateValue(value: any): void;
    type?: string;
    options?: string[]; // Optional prop for select input
}

interface ModalProps {
    closeModal(): void;
}

// Update the Input component to handle select inputs
const Input = ({ label, value, updateValue, type = "text", options }: InputProps) => {
    return (
        <div className="mb-3">
            <label className="form-label">{label}</label>
            {type === 'select' ? (
                <select className="form-select" value={typeof value === 'object' && value instanceof Decimal ? value.toString() : value} onChange={(e) => updateValue(e.target.value)}>
                    {options?.map(option => (
                        <option key={option} value={option}>{option}</option>
                    ))}
                </select>
            ) : (
                <input
                    className="form-control"
                    type={type}
                    value={typeof value === 'object' && value instanceof Decimal ? value.toString() : value} // Convert Decimal to string
                    onChange={(e) => {
                        const newValue = type === "number" ? e.target.valueAsNumber : e.target.value;
                        updateValue(newValue);
                    }}
                />
            )}
        </div>
    );
};

// export interface DadosProdutos {
//     codProd?: string,
//     nome: string,
//     preco: Decimal,
//     qtdEstoque: number,
//     categoria: string,
//     imagemUrl: string
// }
export function CadastrarProdutos({ closeModal }: ModalProps) {
    const [nome, setNome] = useState('');
    const [preco, setPreco] = useState<Decimal>(new Decimal(0));
    const [qtdEstoque, setQtdEstoque] = useState('');
    const [categoria, setCategoria] = useState('');
    const [imagemUrl, setImagemUrl] = useState('');

    const { mutate, isSuccess, isPending } = useDadosProdutosMutate();

    const submit = () => {
        const dadosProdutos: DadosProdutos = {
            nome,
            preco,
            qtdEstoque:parseInt(qtdEstoque),
            categoria,
            imagemUrl
        };

        mutate(dadosProdutos);
    };

    useEffect(() => {
        if (!isSuccess) return;
        closeModal();
    }, [isSuccess]);

    return (
        <div className="modal fade show d-block" tabIndex={-1} role="dialog">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Cadastrar Novo Cliente</h5>
                    <button type="button" className="btn-close" aria-label="Close" onClick={closeModal}></button>
                    </div>
                    <div className="modal-body">
                        <form>
                            <Input label="Nome:" value={nome} updateValue={setNome} />
                            <Input label="Preco:" value={preco} updateValue={setPreco} />
                            <Input label="Quantidade em Estoque:" value={qtdEstoque} updateValue={setQtdEstoque}/>
                            <Input label="Categoria:" value={categoria} updateValue={setCategoria} />
                            <Input label="ImagemUrl:" value={imagemUrl} updateValue={setImagemUrl} />
                        </form>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-primary" onClick={submit}> {isPending ? "Cadastrando..." : "Cadastrar"} </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
