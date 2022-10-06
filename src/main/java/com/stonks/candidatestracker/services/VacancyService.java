package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.VacancyInsertDto;
import com.stonks.candidatestracker.dto.responses.VacancyGetResponseDto;
import com.stonks.candidatestracker.enums.ContractType;
import com.stonks.candidatestracker.enums.Country;
import com.stonks.candidatestracker.models.UserModel;
import com.stonks.candidatestracker.models.VacancyModel;
import com.stonks.candidatestracker.repositories.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Transactional(readOnly = true)
    public List<VacancyGetResponseDto> findAllOpenVacancies() {
        List<VacancyModel> vacancies = vacancyRepository.findAll().stream().filter(x -> x.isConcluded() == false).collect(Collectors.toList());
        return vacancies.stream().map(vacancy -> new VacancyGetResponseDto(vacancy)).collect(Collectors.toList());
    }

    @Transactional
    public void createVacancy(VacancyInsertDto vacancyInsertDto) {
        VacancyModel vacancyModel = new VacancyModel();
        vacancyModel.setCreator(new UserModel(2L)); // auth user recruiter
        vacancyModel.setConcluded(Boolean.FALSE);
        vacancyModel.setDescription(vacancyInsertDto.getDescription());
        vacancyModel.setCountry(vacancyInsertDto.getCountry());
        vacancyModel.setContractType(vacancyInsertDto.getContractType());
        vacancyModel.setName(vacancyInsertDto.getName());
        vacancyRepository.save(vacancyModel);
    }

}
