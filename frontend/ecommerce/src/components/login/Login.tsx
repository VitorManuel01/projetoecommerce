import React, { useState } from "react";
import { useDadosLoginMutate } from "../../hooks/useDadosLoginMutate";

const Login = () => {
  const [emailOrLogin, setEmailOrLogin] = useState("");
  const [senha, setSenha] = useState("");
  const { mutate, isPending, error, isSuccess } = useDadosLoginMutate();

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    mutate({ emailOrLogin, senha })
  }

  return (
      <div>
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <div>
            <label>Email or Login:</label>
            <input
              type="text"
              value={emailOrLogin}
              onChange={(e) => setEmailOrLogin(e.target.value)}
            />
          </div>
          <div>
            <label>Senha:</label>
            <input
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
            />
          </div>
          <button type="submit" disabled={isPending}>
            {isPending ? "Carregando..." : "Login"}
          </button>
          {isSuccess && <p style={{ color: "green" }}>Login realizado com sucesso!</p>}
          {error && <p style={{ color: "red" }}>Erro ao realizar login</p>}

        </form>
      </div>
  );
};

export default Login;