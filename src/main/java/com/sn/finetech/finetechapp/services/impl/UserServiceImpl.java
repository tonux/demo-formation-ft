package com.sn.finetech.finetechapp.services.impl;

import com.sn.finetech.finetechapp.model.Role;
import com.sn.finetech.finetechapp.model.User;
import com.sn.finetech.finetechapp.repositories.UserRepository;
import com.sn.finetech.finetechapp.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User registration(User userRequest) throws ValidationException {
        // verifier si l'utilisateur existe
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()){
            throw new ValidationException("L'utilisateur existe déjà");
        }

        if(userRequest.getAuthorities() == null || userRequest.getAuthorities().isEmpty()){
            userRequest.setAuthorities(Set.of(new Role(Role.USER)));
        }
        User user = userRequest;

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User login(User userRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

        return (User) authentication.getPrincipal();
    }
}
