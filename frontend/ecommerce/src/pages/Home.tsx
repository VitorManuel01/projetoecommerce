import React from 'react';

const Home: React.FC = () => {
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
          <div className="col-md-3">
            <div className="card">
              <img src="https://via.placeholder.com/150" className="card-img-top" alt="Livro 1" />
              <div className="card-body">
                <h5 className="card-title">Livro 1</h5>
                <p className="card-text">Uma breve descrição do livro.</p>
                <a href="#" className="btn btn-primary">Comprar</a>
              </div>
            </div>
          </div>
          <div className="col-md-3">
            <div className="card">
              <img src="https://via.placeholder.com/150" className="card-img-top" alt="Livro 2" />
              <div className="card-body">
                <h5 className="card-title">Livro 2</h5>
                <p className="card-text">Uma breve descrição do livro.</p>
                <a href="#" className="btn btn-primary">Comprar</a>
              </div>
            </div>
          </div>
          <div className="col-md-3">
            <div className="card">
              <img src="https://via.placeholder.com/150" className="card-img-top" alt="Livro 3" />
              <div className="card-body">
                <h5 className="card-title">Livro 3</h5>
                <p className="card-text">Uma breve descrição do livro.</p>
                <a href="#" className="btn btn-primary">Comprar</a>
              </div>
            </div>
          </div>
          <div className="col-md-3">
            <div className="card">
              <img src="https://via.placeholder.com/150" className="card-img-top" alt="Livro 4" />
              <div className="card-body">
                <h5 className="card-title">Livro 4</h5>
                <p className="card-text">Uma breve descrição do livro.</p>
                <a href="#" className="btn btn-primary">Comprar</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;