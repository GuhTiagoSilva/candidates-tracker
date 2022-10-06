package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.models.UserModel;
import com.stonks.candidatestracker.repositories.UserRepository;
import com.stonks.candidatestracker.services.exceptions.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository repository;

    public AuthService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserModel authenticated() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName(); // get the current username logged
            return repository.findByEmail(username);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid User");
        }
    }
}
