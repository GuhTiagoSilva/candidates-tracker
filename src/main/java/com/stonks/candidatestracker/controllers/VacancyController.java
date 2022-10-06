package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.dto.VacancyInsertDto;
import com.stonks.candidatestracker.dto.responses.VacancyGetResponseDto;
import com.stonks.candidatestracker.services.VacancyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    @ApiOperation(value = "Obter todas as vagas abertas")
    @PreAuthorize("hasAnyRole('WORKER')")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyGetResponseDto> findAllVacanciesOpened() {
        return vacancyService.findAllOpenVacancies();
    }

    @PostMapping("/create")
    @ApiOperation(value = "Criar uma vaga")
    @PreAuthorize("hasAnyRole('RECRUITER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVacancy(@RequestBody VacancyInsertDto vacancyInsertDto) {
        vacancyService.createVacancy(vacancyInsertDto);
    }

}
