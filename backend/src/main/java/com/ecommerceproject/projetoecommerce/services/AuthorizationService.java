package com.ecommerceproject.projetoecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerceproject.projetoecommerce.domain.usuarios.Usuario;
import com.ecommerceproject.projetoecommerce.repositories.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService {


    @Autowired
    UsuarioRepository usuarioRepository;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     if(repository.findByEmail(username) != null){
    //         return repository.findByEmail(username);
    //     }else{
    //         return repository.findByLogin(username);
    //     }
        
    // }

       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           // Busca o usuário pelo email
           Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(username);
       
           // Busca o usuário pelo login
           Optional<Usuario> usuarioByLogin = usuarioRepository.findByLogin(username);
       
           // Verifica se algum dos dois retornou um usuário e retorna o primeiro encontrado
           if (usuarioByEmail.isPresent()) {
               return usuarioByEmail.get();
           } else if (usuarioByLogin.isPresent()) {
               return usuarioByLogin.get();
           }
       
           // Se nenhum dos dois retornou, lança exceção
           throw new UsernameNotFoundException("Usuário não encontrado: " + username);
       }
}
