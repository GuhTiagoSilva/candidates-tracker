package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.dto.UserInsertDto;
import com.stonks.candidatestracker.dto.UserUpdateDto;
import com.stonks.candidatestracker.dto.responses.UserWorkerGetResponseDto;
import com.stonks.candidatestracker.services.UserService;
import com.stonks.candidatestracker.services.UserWorkerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public UserWorkerGetResponseDto findAuthUserInformation() {
        return userService.findAuthUserInformation();
    }

    @GetMapping
    @ApiOperation(value = "Encontrar todos os usuários que estão open to work")
    @PreAuthorize("hasAnyRole( 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserWorkerGetResponseDto> findAllOpenToWork() {
        return userWorkerService.findAllUsersOpenToWork();
    }

    @PutMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "Atualizar um usuário")
    @PreAuthorize("hasAnyRole('WORKER', 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long userId,
                           @RequestPart(value = "user") UserUpdateDto userUpdateDto,
                           @RequestPart(name = "image", required = false) MultipartFile multipartFile
    ) throws IOException {
        userService.updateUser(userId, userUpdateDto, multipartFile);
    }

}
