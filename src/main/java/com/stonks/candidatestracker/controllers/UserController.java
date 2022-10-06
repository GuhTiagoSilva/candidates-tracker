package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.dto.UserInsertDto;
import com.stonks.candidatestracker.dto.UserUpdateDto;
import com.stonks.candidatestracker.dto.responses.UserWorkerGetResponseDto;
import com.stonks.candidatestracker.services.UserService;
import com.stonks.candidatestracker.services.UserWorkerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/signup")
    @ApiOperation(value = "Criar um usuário")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserInsertDto userInsertDto) {
        userService.createUser(userInsertDto);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Encontrar um usuário por ID")
    @ResponseStatus(HttpStatus.OK)
    public UserWorkerGetResponseDto findById(@PathVariable Long id) {
        return userWorkerService.findById(id);
    }

    @GetMapping("authenticated")
    @ApiOperation(value = "Achar informações do usuário autenticado")
    @PreAuthorize("hasAnyRole('WORKER', 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findAuthUserInformation() {
        return userService.findAuthUserInformation();
    }

    @GetMapping
    @ApiOperation(value = "Encontrar todos os usuários que estão open to work")
    @PreAuthorize("hasAnyRole( 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserWorkerGetResponseDto> findAllOpenToWork() {
        return userWorkerService.findAllUsersOpenToWork();
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Atualizar um usuário")
    @PreAuthorize("hasAnyRole('WORKER', 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(userId, userUpdateDto);
    }

}
