import React, { useState } from 'react';
import { useDadosClientesMutate } from '../../hooks/useDadosClientesMutate';
import { DadosClientes } from '../../interface/DadosClientes';
import "./cadastrarCliente.css"

interface InputProps {
    label: string;
    value: string | number;
    updateValue(value: any): void;
    type?: string; // Optional prop to specify input type
}

const Input = ({ label, value, updateValue, type = "text" }: InputProps) => {
    return (
        <>
            <label>{label}</label>
            <input type={type} value={value}onChange={(e) => updateValue(e.target.value)}></input>
        </>
    );
};


export function CadastrarClientes() {
    const [nomeCliente, setNome] = useState('');
    const [CPF, setCPF] = useState('');
    const [sexo, setSexo] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [CEP, setCEP] = useState('');
    const [bairro, setBairro] = useState('');
    const [telefone, setTelefone] = useState('');

    const { mutate } = useDadosClientesMutate();

    const submit = () =>{
        const dadosClientes: DadosClientes = {
            nomeCliente,
            CPF,
            sexo,
            dataNascimento,
            CEP,
            bairro,
            telefone
        }

        mutate(dadosClientes)
    }

    return (
        <div className="modal-overlay">
            <div className="modal-body">
                <h2>Cadastrar novo cliente</h2>
                <form action="" method="post" className="input-container">
                    <Input label="Nome:" value={nomeCliente} updateValue={setNome} />
                    <Input label="CPF:" value={CPF} updateValue={setCPF} />
                    <Input label="Sexo:" value={sexo} updateValue={setSexo} />
                    <Input label="Data de Nascimento:" value={dataNascimento} updateValue={setDataNascimento} type="date"/>
                    <Input label="CEP:" value={CEP} updateValue={setCEP} />
                    <Input label="Bairro:" value={bairro} updateValue={setBairro} />
                    <Input label="Telefone:" value={telefone} updateValue={setTelefone} />
                </form>
            </div>

            <button onClick={submit} className='btn-secondary'>Cadastrar</button>
        </div>
    );
}