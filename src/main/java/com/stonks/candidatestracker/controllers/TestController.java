package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.dto.TestInsertDto;
import com.stonks.candidatestracker.dto.responses.TestGetResponseDto;
import com.stonks.candidatestracker.dto.responses.TestRevisionDto;
import com.stonks.candidatestracker.services.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @ApiOperation("Listar todos os testes da aplicação")
    @GetMapping
    @PreAuthorize("hasAnyRole('WORKER', 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public List<TestGetResponseDto> findAll() {
        return testService.findAllTests();
    }

    @ApiOperation("Listar todos os testes da aplicação buscando por um skill")
    @GetMapping(value = "/{skillId}")
    @PreAuthorize("hasAnyRole('WORKER', 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public List<TestGetResponseDto> findBySkill(@PathVariable Long skillId) {
        return testService.findBySkill(skillId);
    }

    @ApiOperation("Resultado do teste")
    @GetMapping("/{testId}/revision")
    @PreAuthorize("hasAnyRole('WORKER', 'RECRUITER')")
    @ResponseStatus(HttpStatus.OK)
    public TestRevisionDto correctTest(@PathVariable Long testId, @RequestBody TestInsertDto testInsertDto) {
        return testService.correctTest(testId, testInsertDto);
    }

}
