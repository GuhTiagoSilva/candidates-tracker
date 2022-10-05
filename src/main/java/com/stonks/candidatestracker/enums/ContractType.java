package com.stonks.candidatestracker.enums;

import lombok.Getter;

public enum ContractType {

    FULLTIME("Full-Time"), PARTIME("Part-Time"), FREELANCE("Freelance");

    @Getter
    private String value;

    ContractType(String value) {
        this.value = value;
    }

}
