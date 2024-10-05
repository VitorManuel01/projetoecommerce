// package com.ecommerceproject.projetoecommerce.services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.ecommerceproject.projetoecommerce.repositories.ClienteRepository;

// @Service
// public class AuthorizationServiceCliente implements UserDetailsService {


//     @Autowired
//     ClienteRepository repository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         if(repository.findByEmail(username) != null){
//             return repository.findByEmail(username);
//         }else{
//             return repository.findByLogin(username);
//         }
        
//     }
// }
