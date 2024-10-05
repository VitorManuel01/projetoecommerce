import { useEffect, useState } from 'react';
import { useDadosClientesMutate } from '../../hooks/useDadosClientesMutate';
import { DadosClientes } from '../../interface/dadosClientes';
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa o CSS do Bootstrap
import './cadastrarCliente.css'; // Importe seu CSS customizado se necessário

interface InputProps {
    label: string;
    value: string | number;
    updateValue(value: any): void;
    type?: string;
    options?: string[]; // Optional prop for select input
}

interface ModalProps {
    closeModal(): void;
}

const Input = ({ label, value, updateValue, type = "text", options }: InputProps) => {
    return (
        <div className="mb-3">
            <label className="form-label">{label}</label>
            {type === 'select' ? (
                <select className="form-select" value={value} onChange={(e) => updateValue(e.target.value)}>
                    {options?.map(option => (
                        <option key={option} value={option}>{option}</option>
                    ))}
                </select>
            ) : (
                <input
                    className="form-control"
                    type={type}
                    value={value}
                    onChange={(e) => updateValue(e.target.value)}
                />
            )}
        </div>
    );
};

export function CadastrarClientes({ closeModal }: ModalProps) {
    const [login, setLogin] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [nomeCliente, setNome] = useState('');
    const [CPF, setCPF] = useState('');
    const [sexo, setSexo] = useState('M');
    const [dataNascimento, setDataNascimento] = useState('');
    const [CEP, setCEP] = useState('');
    const [bairro, setBairro] = useState('');
    const [telefone, setTelefone] = useState('');

    const { mutate, isSuccess: isCadastroSuccsess, isPending } = useDadosClientesMutate();

    const submit = () => {
        const dadosClientes: DadosClientes = {
            login,
            email,
            senha,
            nomeCliente,
            CPF: CPF.replace(/\D/g, ''), // Remover caracteres não numéricos do CPF
            sexo,
            dataNascimento, // O campo está sendo enviado diretamente, já formatado como string
            CEP: CEP.replace(/\D/g, ''), // Remover caracteres não numéricos do CEP
            bairro,
            telefone
        };

        mutate(dadosClientes);
    };

    useEffect(() => {
        if (isCadastroSuccsess) {
            closeModal(); // Fecha o modal após o sucesso
        }
    }, [isCadastroSuccsess]);

    return (
        <div className="modal fade show d-block" tabIndex={-1} role="dialog">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Cadastrar</h5>
                        <button type="button" className="btn-close" aria-label="Close" onClick={closeModal}></button>
                    </div>
                    <div className="modal-body">
                        <form>
                            <Input label="Login:" value={login} updateValue={setLogin} />
                            <Input label="Email:" value={email} updateValue={setEmail} />
                            <Input label="Senha:" value={senha} updateValue={setSenha} type="password" />
                            <Input label="Nome:" value={nomeCliente} updateValue={setNome} />
                            <Input label="CPF:" value={CPF} updateValue={setCPF} />
                            <Input
                                label="Sexo:"
                                value={sexo}
                                updateValue={setSexo}
                                type="select"
                                options={['M', 'F', 'Outro']} // Exemplo de opções
                            />
                            <Input label="Data de Nascimento:" value={dataNascimento} updateValue={setDataNascimento} type="date" />
                            <Input label="CEP:" value={CEP} updateValue={setCEP} />
                            <Input label="Bairro:" value={bairro} updateValue={setBairro} />
                            <Input label="Telefone:" value={telefone} updateValue={setTelefone} />
                        </form>
                        
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-primary" onClick={submit}>
                            {isPending ? "Cadastrando..." : "Cadastrar"}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
