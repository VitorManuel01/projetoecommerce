import { useState } from 'react';
import { Produtos } from '../components/produtos/produtos';
import { useDadosProdutos } from '../hooks/useDadosProdutos';
import { CadastrarProdutos } from "../components/cadastros/inserirProdutos"
import { useAuth } from '../context/AuthProvider';
import "../styles/ProdutoList.css"

function ProdutoList() {
  const { data } = useDadosProdutos();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const { isAuthenticated, logout } = useAuth();
  const handleOpenModal = () => {
    setIsModalOpen(prev => !prev)
  }

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Livros</h1>
      <div className="row">
        {data?.map(dadosProdutos => (
          <div className="col-md-4 col-sm-6 mb-4" key={dadosProdutos.codProd}>
            <Produtos 
              codProd={dadosProdutos.codProd}
              nome={dadosProdutos.nome}
              preco={dadosProdutos.preco}
              qtdEstoque={dadosProdutos.qtdEstoque}
              categoria={dadosProdutos.categoria}
              imagemUrl={dadosProdutos.imagemUrl} 
            />
          </div>
        ))}
      </div>

      {isAuthenticated && (
        <div className="text-center mt-4">
          <button className="btn btn-primary me-2" onClick={handleOpenModal}>Novo Cadastro</button>
          <button className="btn btn-secondary" onClick={logout}>Logout</button>
        </div>
      )}

      {isModalOpen && <CadastrarProdutos closeModal={handleOpenModal} />}
    </div>
  );
}

export default ProdutoList;

