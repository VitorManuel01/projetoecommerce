import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import App from './App.tsx'
import './index.css'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { AuthProvider } from './context/AuthProvider.tsx';
import './config/axiosConfig.ts';



const queryClient = new QueryClient();

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <AuthProvider>
      <App />
      </AuthProvider>
      </QueryClientProvider>
    
  </StrictMode>,
)

// createRoot: Aqui, createRoot é 
// chamado diretamente, sem o prefixo ReactDOM. 
// Isso significa que createRoot foi importado diretamente no arquivo, 
// em: import { createRoot } from 'react-dom/client';
//-----------------------------------------------------------------//
// as HTMLElement: Use quando você precisa garantir que o tipo do 
// valor seja HTMLElement e quer evitar qualquer potencial problema de tipagem.
// !: Use quando você quer simplesmente garantir que o valor não seja null 
// ou undefined, sem alterar o tipo inferido.
