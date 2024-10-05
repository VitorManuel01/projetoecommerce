import { useDadosProdutos } from '../hooks/useDadosProdutos'; // Ajuste o caminho conforme necessário
import { useCarrinho  } from '../hooks/useDadosCarrinho'; // Importando o hook do carrinho
import { ComprarProdutoRequestDTO } from '../interface/ComprarProdutoRequestDTO';


const Home: React.FC = () => {

  const { data: produtos, isLoading, error } = useDadosProdutos();
  const {addItem } = useCarrinho();



  const handleComprar = (codProd: string, quantidade: number) => {
      const data: ComprarProdutoRequestDTO = { codProd, quantidade };
      addItem(data);
  };

  if (isLoading) {
      return <div>Carregando...</div>;
  }

  if (error) {
      return <div>Erro ao carregar produtos: {error.message}</div>;
  }


  return (
    <div>
      {/* Carrossel */}
      <div id="carouselExampleCaptions" className="carousel slide" data-bs-ride="carousel">
        <div className="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div className="carousel-inner">
          <div className="carousel-item active">
            <img src="https://via.placeholder.com/1200x400?text=Livro+1" className="d-block w-100" alt="Livro 1" />
            <div className="carousel-caption d-none d-md-block">
              <h5>Livro em Destaque 1</h5>
              <p>Descrição breve do livro 1.</p>
            </div>
          </div>
          <div className="carousel-item">
            <img src="https://via.placeholder.com/1200x400?text=Livro+2" className="d-block w-100" alt="Livro 2" />
            <div className="carousel-caption d-none d-md-block">
              <h5>Livro em Destaque 2</h5>
              <p>Descrição breve do livro 2.</p>
            </div>
          </div>
          <div className="carousel-item">
            <img src="https://via.placeholder.com/1200x400?text=Livro+3" className="d-block w-100" alt="Livro 3" />
            <div className="carousel-caption d-none d-md-block">
              <h5>Livro em Destaque 3</h5>
              <p>Descrição breve do livro 3.</p>
            </div>
          </div>
        </div>
        <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Anterior</span>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Próximo</span>
        </button>
      </div>

      {/* Lista de Livros */}
      <div className="container mt-5">
        <h2>Livros Populares</h2>
        <div className="row">
          {produtos && produtos.map(produto => (
            <div className="col-md-3" key={produto.codProd}> {/* Usar o ID como chave */}
              <div className="card">
                <img src={produto.imagemUrl} className="card-img-top" alt={produto.nome} />
                <div className="card-body">
                  <h5 className="card-title">{produto.nome}</h5>
                  <p className="card-text">Preço: R$ {produto.preco.toFixed(2)}</p>
                  <p className="card-text">{produto.qtdEstoque} em estoque</p>
                  <button className="btn btn-primary"
                    onClick={() => {
                      if (produto.codProd) {
                        handleComprar(produto.codProd, 1); // Chama a função apenas se codProd estiver definido
                      }else{
                        console.log("SE FUDEU OTÁRIO");
                      }
                    }}>Comprar</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Home;
