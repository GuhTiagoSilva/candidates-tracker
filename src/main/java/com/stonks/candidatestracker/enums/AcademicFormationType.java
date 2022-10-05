package com.stonks.candidatestracker.enums;

import lombok.Getter;

import java.util.Arrays;

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


    public static AcademicFormationType fromValue(String value) {
        return Arrays
                .stream(AcademicFormationType.values())
                .filter(academicFormationType -> academicFormationType.getValue()
                        .equals(value))
                .findFirst().get();
    }

}
