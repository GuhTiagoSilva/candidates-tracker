package com.stonks.candidatestracker.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_certification")
public class CertificationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private SkillModel skill = new SkillModel();
    @ManyToMany
    @JoinTable(
            name = "tb_user_certificates",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserModel> users = new HashSet<>();
    private LocalDate emitted;

}
