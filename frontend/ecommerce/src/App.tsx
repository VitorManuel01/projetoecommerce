import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import ProdutosList from './pages/ProdutosList';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './components/login/Login';
import Home from './pages/Home';
import { useEffect, useState } from 'react';
import { createCarrinho, fetchCarrinho } from './hooks/useDadosCarrinho';
import { Carrinho } from './interface/Carrinho';
import Cookies from 'js-cookie';
import { SeuCarrinho } from './components/carrinho/carrinho';

function App() {
  const [cartId, setCartId] = useState<string | null>(null);
  const [cart, setCart] = useState<Carrinho | null>(null);
  const existingCartId = Cookies.get('cartId');
  const [isModalOpen, setIsModalOpen] = useState(false);
  
  useEffect(() => {
    const initializeCart = async () => {
      try {
        const id = await createCarrinho(); // Criar o carrinho
        setCartId(id); // Armazena o ID do carrinho
        Cookies.set('cartId', id); // Armazena o ID do carrinho no cookie
        const fetchedCart = await fetchCarrinho(id); // Busca o carrinho criado
        setCart(fetchedCart); // Armazena os dados do carrinho
      } catch (error) {
        console.error("Error ao iniciar carrinho:", error);
      }
    };

    // Verificar se já existe um carrinho no cookie
    if (existingCartId) {
      setCartId(existingCartId); // Definir o ID do carrinho a partir do cookie

      fetchCarrinho(existingCartId)
        .then(fetchedCart => {
          setCart(fetchedCart);
        })
        .catch(error => {
          console.error("Erro ao pegar carrinho:", error);
        });
    } else {
      // Se não houver carrinho no cookie, inicializar um novo
      initializeCart();
    }
  }, []);

  const handleOpenModal = () => {
    setIsModalOpen(prev => !prev)
  }
  return (
    <Router>
      <div className="container">
        {/* Barra de Navegação */}
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid">
            <Link className="navbar-brand" to="/">E-Livros</Link>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav ms-auto">
                <li className="nav-item">
                  <Link className="nav-link" to="/">Home</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/produtos">Catálogo</Link>
                </li>
                <li className="nav-item">
                <Link className="nav-link" to="/"onClick={handleOpenModal}>Carrinho</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">Login</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        {isModalOpen && <SeuCarrinho closeModal={handleOpenModal} />}
        {/* Conteúdo da Página */}
        <div className="mt-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/produtos" element={<ProdutosList />} />
            <Route path="/login" element={<Login />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;

