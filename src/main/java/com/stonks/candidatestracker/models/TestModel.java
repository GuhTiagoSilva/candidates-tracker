package com.stonks.candidatestracker.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private SkillModel skill = new SkillModel();
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private List<QuestionModel> questions = new ArrayList<>();
    private int numberOfQuestionsToBeApproved;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

}
