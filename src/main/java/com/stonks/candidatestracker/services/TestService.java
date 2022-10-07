package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.TestInsertDto;
import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.dto.responses.TestGetResponseDto;
import com.stonks.candidatestracker.dto.responses.TestRevisionDto;
import com.stonks.candidatestracker.models.*;
import com.stonks.candidatestracker.models.pk.AnswerQuestionPK;
import com.stonks.candidatestracker.repositories.*;
import com.stonks.candidatestracker.services.exceptions.BusinessException;
import com.stonks.candidatestracker.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    private final TestRepository testRepository;

    private final SkillRepository skillRepository;

    private final AnswerRepository answerRepository;

    private final AuthService authService;

    private final AnswerQuestionRepository answerQuestionRepository;

    private final QuestionRepository questionRepository;

    public TestService(TestRepository testRepository,
                       SkillRepository skillRepository,
                       AnswerRepository answerRepository,
                       AnswerQuestionRepository answerQuestionRepository,
                       QuestionRepository questionRepository,
                       AuthService authService) {
        this.testRepository = testRepository;
        this.skillRepository = skillRepository;
        this.answerRepository = answerRepository;
        this.authService = authService;
        this.answerQuestionRepository = answerQuestionRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<TestGetResponseDto> findAllTests() {
        List<TestModel> tests = testRepository.findAll();
        return tests.stream().map(test -> new TestGetResponseDto(test)).collect(Collectors.toList());
    }

    @Transactional
    public List<TestGetResponseDto> findBySkill(Long skillId) {
        SkillModel skill = skillRepository.findById(skillId).orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        return testRepository.findAllBySkill(skill).stream().map(test -> new TestGetResponseDto(test)).collect(Collectors.toList());
    }

    @Transactional
    public TestRevisionDto correctTest(Long testId, TestInsertDto testInsertDto) {
        try {
            UserModel authUser = authService.authenticated();
            TestModel testModel = testRepository.findById(testId).orElseThrow(() -> new ResourceNotFoundException("Test not found"));

            var correctAnswers = new Object() {
                int correctAnswers = 0;
            };

            testInsertDto.getQuestions().forEach(question -> {

                final AnswerQuestionPK answerQuestionPK = new AnswerQuestionPK();
                final AnswerModel answerModel = answerRepository.findById(question.getMarkedAnswerId()).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
                final QuestionModel questionModel = questionRepository.findById(question.getId()).orElseThrow(() -> new ResourceNotFoundException("Question Not Found"));

                answerQuestionPK.setAnswerModel(answerModel);
                answerQuestionPK.setQuestionModel(questionModel);

                if (answerQuestionRepository.existsById(answerQuestionPK))
                    correctAnswers.correctAnswers++;

            });
            if (correctAnswers.correctAnswers >= testModel.getNumberOfQuestionsToBeApproved()) {
                testModel.setUser(authUser);
                testRepository.save(testModel);
                return new TestRevisionDto(new UserDto(authUser), correctAnswers.correctAnswers, true, testInsertDto.getSkill());
            } else {
                throw new BusinessException("Not approved in exam.");
            }
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Not Found.");
        }
    }

}
