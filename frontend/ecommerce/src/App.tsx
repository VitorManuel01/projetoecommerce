// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'

import { Clientes } from './components/usuarios/clientes';
// import { dadosClientes } from './interface/dadosClientes';
import { useDadosClientes } from './hooks/useDadosClientes';

function App() {
  const { data } = useDadosClientes();
  return (
    <>
      <div className="container">
        <h1>Clientes</h1>
        <div className="card-grid">
          {data?.map(dadosClientes => <Clientes key={dadosClientes.id} id={dadosClientes.id} codCliente={dadosClientes.codCliente} nomeCliente={dadosClientes.nomeCliente} CPF={dadosClientes.CPF} sexo={dadosClientes.sexo} dataNascimento={dadosClientes.dataNascimento}
            CEP={dadosClientes.CEP} bairro={dadosClientes.bairro} telefone={dadosClientes.telefone} />)}
        </div>
      </div>

    </>
  )
}

export default App
