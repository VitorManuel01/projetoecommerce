// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
//import { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import ClienteList from './pages/ClienteList';
import ProdutosList from './pages/ProdutosList';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';


function App() {
  return (
    <Router>
      <div className="container">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="nav-link" to="/">Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/clientes">Lista de Clientes</Link>
                <Link className="nav-link" to="/produtos">Lista de Produtos</Link>
              </li>
            </ul>
          </div>
        </nav>
        <div className="mt-4">
          <Routes>
            <Route path="/produtos" element={<ProdutosList />} />
            <Route path="/clientes" element={<ClienteList />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;

