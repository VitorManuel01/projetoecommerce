import 'bootstrap/dist/css/bootstrap.min.css'; // Certifique-se de importar o Bootstrap no seu projeto
import './produtos.css'; // Importe seu CSS customizado se necessário
import { Decimal } from 'decimal.js';
import { useAuth } from '../../context/AuthProvider';
import { deleteDadosProdutos } from '../../hooks/deleteDadosProduto';
import { updateDadosProdutos } from '../../hooks/updateDadosProduto';
import { useState } from 'react';

interface ProdutosProps {
    codProd?: string,
    nome: string,
    preco: Decimal,
    qtdEstoque: number,
    categoria: string,
    imagemUrl: string
}

interface InputProps {
  label: string;
  value: string | number | Decimal;
  updateValue(value: any): void;
  type?: string;
  options?: string[]; // Optional prop for select input
}
const Input = ({ label, value, updateValue, type = "text", options }: InputProps) => {
  return (
      <div className="mb-3">
          <label className="form-label">{label}</label>
          {type === 'select' ? (
              <select className="form-select" value={typeof value === 'object' && value instanceof Decimal ? value.toString() : value} onChange={(e) => updateValue(e.target.value)}>
                  {options?.map(option => (
                      <option key={option} value={option}>{option}</option>
                  ))}
              </select>
          ) : (
              <input
                  className="form-control"
                  type={type}
                  value={typeof value === 'object' && value instanceof Decimal ? value.toString() : value} // Convert Decimal to string
                  onChange={(e) => {
                      const newValue = type === "number" ? e.target.valueAsNumber : e.target.value;
                      updateValue(newValue);
                  }}
              />
          )}
      </div>
  );
};

export function Produtos({
  codProd,
  nome,
  preco,
  qtdEstoque,
  categoria,
  imagemUrl
}: ProdutosProps) {

  const { isAuthenticated } = useAuth();
  const { mutate: Deletar } = deleteDadosProdutos();
  const { mutate: Atualizar } = updateDadosProdutos();

  // Estado para controlar o modo de edição
  const [isEditing, setIsEditing] = useState(false);
  const [editNome, setEditNome] = useState(nome);
  const [editPreco, setEditPreco] = useState(preco);
  const [editQtdEstoque, setEditQtdEstoque] = useState(qtdEstoque);
  const [editCategoria, setEditCategoria] = useState(categoria);
  const [editImagemUrl, setEditImagemUrl] = useState(imagemUrl);

  const handleDelete = () => {
      if (codProd) {
          Deletar(codProd);
      } else {
          console.error("Código do produto não definido.");
      }
  };

  const handleUpdate = () => {
      if (codProd) {
          Atualizar({
              codProd,
              data: {
                  nome: editNome,
                  preco: editPreco,
                  qtdEstoque: editQtdEstoque,
                  categoria: editCategoria,
                  imagemUrl: editImagemUrl,
              }
          });
          setIsEditing(false); // Sair do modo de edição
      }
  };

  const handleCancel = () => {
      // Reverter as alterações ao cancelar a edição
      setEditNome(nome);
      setEditPreco(preco);
      setEditQtdEstoque(qtdEstoque);
      setEditCategoria(categoria);
      setEditImagemUrl(imagemUrl);
      setIsEditing(false); // Sair do modo de edição
  };

  return (
      <div className="card produto-card h-100">
          <img src={imagemUrl} className="card-img-top img-fluid produto-img" alt={nome} />
          <div className="card-body">
              {isEditing ? (
                  <>
                      <Input label="Nome" value={editNome} updateValue={setEditNome} />
                      <Input label="Preço" value={editPreco} updateValue={setEditPreco} type="number" />
                      <Input label="Quantidade em Estoque" value={editQtdEstoque} updateValue={setEditQtdEstoque} type="number" />
                      <Input label="Categoria" value={editCategoria} updateValue={setEditCategoria} />
                      <Input label="URL da Imagem" value={editImagemUrl} updateValue={setEditImagemUrl} />
                      
                      <button className="btn btn-success" onClick={handleUpdate}>Salvar</button>
                      <button className="btn btn-secondary" onClick={handleCancel}>Cancelar</button>
                  </>
              ) : (
                  <>
                      <h5 className="card-title">{nome}</h5>
                      <ul className="list-unstyled">
                          <li><strong>Preço:</strong> R$ {preco.toFixed(2)}</li> {/* Formatação adequada para 2 casas decimais */}
                          <li><strong>Quantidade em Estoque:</strong> {qtdEstoque}</li>
                          <li><strong>Categoria:</strong> {categoria}</li>
                      </ul>

                      {isAuthenticated && (
                          <>
                              <button className="btn btn-primary" onClick={() => setIsEditing(true)}>Editar</button>
                              <button className="btn btn-secondary" onClick={handleDelete}>Deletar</button>
                          </>
                      )}
                  </>
              )}
          </div>
      </div>
  );
}