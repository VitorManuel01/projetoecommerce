import { useState } from 'react';
import { Clientes } from '../components/usuarios/clientes';
import { useDadosClientes } from '../hooks/useDadosClientes';
import { CadastrarClientes } from "../components/cadastros/cadastrarCliente"

function ClienteList() {
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
  
  export default ClienteList