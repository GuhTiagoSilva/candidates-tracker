package com.stonks.candidatestracker.models;

import com.stonks.candidatestracker.enums.ContractType;
import com.stonks.candidatestracker.enums.Country;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
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
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    private boolean concluded = Boolean.FALSE;
    @ManyToOne
    @JoinColumn(name = "user_creator_id")
    private UserModel creator;
}
