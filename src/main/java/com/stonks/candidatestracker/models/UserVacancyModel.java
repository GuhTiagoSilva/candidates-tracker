package com.stonks.candidatestracker.models;

import com.stonks.candidatestracker.models.pk.UserVacancyPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER_VACANCY")
public class UserVacancyModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserVacancyPK userVacancyPK = new UserVacancyPK();
    @Column(columnDefinition = "TEXT")
    private String resume;
}
