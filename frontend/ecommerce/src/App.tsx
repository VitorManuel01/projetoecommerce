// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import { useState } from 'react';
import './App.css'

import { Clientes } from './components/usuarios/clientes';
// import { dadosClientes } from './interface/dadosClientes';
import { useDadosClientes } from './hooks/useDadosClientes';
import { CadastrarClientes } from "./components/cadastros/cadastrarCliente"

function App() {
  const { data } = useDadosClientes();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () => {
    setIsModalOpen(prev => !prev)
  }

  return (
    <>
      <div className="container">
        <div className="container">
          <h1>Clientes</h1>
          <div className="card-grid">
            {data?.map(dadosClientes => <Clientes key={dadosClientes.id}
              id={dadosClientes.id}
              login={dadosClientes.login}
              email={dadosClientes.email}
              senha={dadosClientes.senha}
              nomeCliente={dadosClientes.nomeCliente}
              CPF={dadosClientes.CPF} sexo={dadosClientes.sexo}
              dataNascimento={dadosClientes.dataNascimento}
              CEP={dadosClientes.CEP} bairro={dadosClientes.bairro}
              telefone={dadosClientes.telefone} />)}
          </div>

          {isModalOpen && <CadastrarClientes closeModal={handleOpenModal} />}
          <button onClick={handleOpenModal}>Novo Cadastro</button>
        </div>
      </div>


    </>
  )
}

export default App
