package com.stonks.candidatestracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_question")
public class QuestionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestModel test;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private AnswerModel answer;
}
