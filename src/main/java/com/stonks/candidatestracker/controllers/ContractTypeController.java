package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.enums.ContractType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("contract-types")
public class ContractTypeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContractType> getContractTypes() {
        return Arrays.asList(ContractType.values());
    }

}
