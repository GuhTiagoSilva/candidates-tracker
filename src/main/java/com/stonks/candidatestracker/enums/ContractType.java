package com.stonks.candidatestracker.enums;

import lombok.Getter;

import java.util.Arrays;

public enum ContractType {

    FULLTIME("Full-Time"), PARTIME("Part-Time"), FREELANCE("Freelance");

    @Getter
    private String value;

    ContractType(String value) {
        this.value = value;
    }

    public static ContractType fromValue(String value) {
        return Arrays
                .stream(ContractType.values())
                .filter(contractType -> contractType.getValue()
                        .equals(value))
                .findFirst().get();
    }

}
