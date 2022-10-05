package com.stonks.candidatestracker.enums;

import lombok.Getter;

public enum AcademicFormationType {

    BACHELOR_DEGREE("ENSINO SUPERIOR"),
    TECHNICAL_DEGREE("ENSINO TÉCNICO"),
    SCHOOL("ENSINO FUNDAMENTAL"),
    HIGH_SCHOOL("ENSINO MÉDIO");

    @Getter
    private String value;

    AcademicFormationType(String value) {
        this.value = value;
    }

}
