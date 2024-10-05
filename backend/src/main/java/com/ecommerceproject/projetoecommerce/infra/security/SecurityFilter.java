package com.ecommerceproject.projetoecommerce.infra.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Usuario;
import com.ecommerceproject.projetoecommerce.repositories.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Recupera o token do request
        var token = this.recoverToken(request);
    
        // Permite que as requisições para essas rotas específicas passem sem validação de token
        if (request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/carrinho/comprar")) {
            filterChain.doFilter(request, response);
            return;
        }
    
        // Se o token estiver presente, tenta validar e autenticar o usuário
    if (token != null) {
        var login = tokenService.validateToken(token);

        if (login != null) {
            // Tenta buscar o usuário pelo email
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(login);
            
            // Se não encontrar pelo email, tenta buscar pelo login
            if (usuarioOpt.isEmpty()) {
                usuarioOpt = usuarioRepository.findByLogin(login);
            }

            // Se o usuário foi encontrado, configura a autenticação
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Retorna erro se o usuário não for encontrado
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não encontrado");
                return;
            }
        } else {
            // Retorna erro se o token for inválido
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
            return;
        }
    }
    
        // Continua o filtro da requisição
        filterChain.doFilter(request, response);
    }
    
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer", "");

    }
}
