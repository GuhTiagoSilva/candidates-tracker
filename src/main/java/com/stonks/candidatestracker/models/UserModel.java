package com.stonks.candidatestracker.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private boolean isOpenToWork;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel roleModel;
    @OneToMany(mappedBy = "worker")
    private List<JobExperienceModel> experiences = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<AcademicFormationModel> formations = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<TestModel> tests = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "tb_user_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillModel> skills = new HashSet<>();
    @OneToMany(mappedBy = "creator")
    private List<VacancyModel> vacancies = new ArrayList<>();
}
