package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.dto.VacancyInsertDto;
import com.stonks.candidatestracker.dto.VacancyUpdateDto;
import com.stonks.candidatestracker.dto.responses.VacancyGetResponseDto;
import com.stonks.candidatestracker.services.VacancyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/{vacancyId}/apply")
    @ApiOperation(value = "Aplicar para uma vaga")
    @PreAuthorize("hasAnyRole('WORKER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void applyUserToVacancy(@PathVariable Long vacancyId, @RequestPart(value = "resume", required = false)MultipartFile multipartFile) {
        vacancyService.applyToVacancy(vacancyId, multipartFile);
    }

    @GetMapping("/creator")
    @ApiOperation(value = "Obter vagas abertas pelo recrutador autenticado")
    @PreAuthorize(value = "hasAnyRole('RECRUITER')")
    public List<VacancyGetResponseDto> findVacancyOpenByRecruiter() {
        return vacancyService.findAllByCreator();
    }

    @PutMapping("/{vacancyId}/update")
    @ApiOperation(value = "Atualizar vaga")
    @PreAuthorize(value = "hasAnyRole('RECRUITER')")
    public void updateVacancy(@PathVariable Long vacancyId, @RequestBody VacancyUpdateDto vacancyUpdateDto) {
        vacancyService.updateVacancy(vacancyId, vacancyUpdateDto);
    }

}
