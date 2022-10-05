package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.dto.responses.UserWorkerGetResponseDto;
import com.stonks.candidatestracker.models.UserModel;
import com.stonks.candidatestracker.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserWorkerService {

    private final UserRepository userRepository;

    public UserWorkerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserWorkerGetResponseDto> findAll() {
        List<UserModel> users = userRepository.findAll();
        return users.stream().map(user -> new UserWorkerGetResponseDto(user)).collect(Collectors.toList());
    }

}
