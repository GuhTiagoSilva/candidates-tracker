package com.stonks.candidatestracker.models;

import com.stonks.candidatestracker.enums.ContractType;
import com.stonks.candidatestracker.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_vacancy")
public class VacancyModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection(targetClass = Country.class)
    @JoinTable(name = "tb_countries", joinColumns = @JoinColumn(name = "vacancy_id"))
    @Enumerated(EnumType.STRING)
    private List<Country> countries = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    private boolean isConcluded = Boolean.FALSE;

}
