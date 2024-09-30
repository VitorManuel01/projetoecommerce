// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
//import { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
//import ClienteList from './pages/ClienteList';
import ProdutosList from './pages/ProdutosList';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './components/login/Login';
import Home from './pages/Home';

function App() {
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
                  <Link className="nav-link" to="/produtos">Lista de Produtos</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">Login</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

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

