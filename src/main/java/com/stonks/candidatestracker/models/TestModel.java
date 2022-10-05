package com.stonks.candidatestracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_test")
public class TestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @OneToOne(mappedBy = "test")
    private SkillModel skill = new SkillModel();
    @OneToMany(mappedBy = "test")
    private List<QuestionModel> questions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

}
