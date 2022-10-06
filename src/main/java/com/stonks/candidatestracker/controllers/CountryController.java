package com.stonks.candidatestracker.controllers;

import com.stonks.candidatestracker.enums.Country;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Country> findAllCountries() {
       return Arrays.asList(Country.values());
    }

}
