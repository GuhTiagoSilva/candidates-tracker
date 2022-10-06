package com.stonks.candidatestracker.models.pk;

import com.stonks.candidatestracker.models.UserModel;
import com.stonks.candidatestracker.models.VacancyModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserVacancyPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyModel vacancyModel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

}
