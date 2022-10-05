package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.dto.UserInsertDto;
import com.stonks.candidatestracker.dto.responses.UserWorkerGetResponseDto;
import com.stonks.candidatestracker.services.UserService;
import com.stonks.candidatestracker.services.UserWorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserWorkerService userWorkerService;

    public UserController(UserService userService, UserWorkerService userWorkerService) {
        this.userService = userService;
        this.userWorkerService = userWorkerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserInsertDto userInsertDto) {
        userService.createUser(userInsertDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserWorkerGetResponseDto> findAll() {
        return userWorkerService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserWorkerGetResponseDto findById(@PathVariable Long id) {
        return userWorkerService.findById(id);
    }

}
