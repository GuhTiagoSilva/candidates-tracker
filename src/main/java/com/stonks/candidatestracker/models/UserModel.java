package com.stonks.candidatestracker.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserModel implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String cpf;
    private Boolean isOpenToWork;
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
    public UserModel(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(this.roleModel.getAuthority()));
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
