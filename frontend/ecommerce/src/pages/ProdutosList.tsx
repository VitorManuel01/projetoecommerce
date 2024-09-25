import { useState } from 'react';
import { Produtos } from '../components/produtos/produtos';
import { useDadosProdutos } from '../hooks/useDadosProdutos';
import { CadastrarProdutos } from "../components/cadastros/inserirProdutos"
import { useAuth } from '../context/AuthProvider';

function ProdutoList() {
  const { data } = useDadosProdutos();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const { isAuthenticated, logout } = useAuth();
  const handleOpenModal = () => {
    setIsModalOpen(prev => !prev)
  }

  return (
    <>
      <div className="container">
        <div className="container">
          <h1>Produtos</h1>
          <div className="card-grid">
            {data?.map(dadosProdutos => <Produtos key={dadosProdutos.codProd}
              codProd={dadosProdutos.codProd}
              nome={dadosProdutos.nome}
              preco={dadosProdutos.preco}
              qtdEstoque={dadosProdutos.qtdEstoque}
              categoria={dadosProdutos.categoria}
              imagemUrl={dadosProdutos.imagemUrl} />)}
          </div>
              
                     
      {isAuthenticated && (
        <>
          <button onClick={handleOpenModal}>Novo Cadastro</button>
          <button onClick={logout}>Logout</button>
          {isModalOpen && <CadastrarProdutos closeModal={handleOpenModal} />}
        </>
      )}
        </div>
      </div>
    </>
  )

}
export default ProdutoList

