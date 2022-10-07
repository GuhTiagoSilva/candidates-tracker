package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.TestInsertDto;
import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.dto.responses.CertificateDto;
import com.stonks.candidatestracker.dto.responses.TestGetResponseDto;
import com.stonks.candidatestracker.models.*;
import com.stonks.candidatestracker.models.pk.AnswerQuestionPK;
import com.stonks.candidatestracker.repositories.*;
import com.stonks.candidatestracker.services.exceptions.BusinessException;
import com.stonks.candidatestracker.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
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

    private final CertificateRepository certificateRepository;

    public TestService(TestRepository testRepository,
                       SkillRepository skillRepository,
                       AnswerRepository answerRepository,
                       AnswerQuestionRepository answerQuestionRepository,
                       QuestionRepository questionRepository,
                       CertificateRepository certificateRepository,
                       AuthService authService) {
        this.testRepository = testRepository;
        this.skillRepository = skillRepository;
        this.answerRepository = answerRepository;
        this.authService = authService;
        this.answerQuestionRepository = answerQuestionRepository;
        this.questionRepository = questionRepository;
        this.certificateRepository = certificateRepository;
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
    public CertificateDto correctTest(Long testId, TestInsertDto testInsertDto) {
        try {
            UserModel authUser = authService.authenticated();
            TestModel testModel = testRepository.findById(testId).orElseThrow(() -> new ResourceNotFoundException("Test not found"));
            return correctTests(testInsertDto, testModel, authUser);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Not Found.");
        }
    }

    private CertificateDto correctTests(TestInsertDto testInsertDto, TestModel testModel, UserModel authUser) {
        var correctAnswers = getCorrectedAnswers(testInsertDto);

        if (correctAnswers >= testModel.getNumberOfQuestionsToBeApproved()) {

            SkillModel skill = skillRepository.findById(testModel.getSkill().getId()).orElseThrow(() -> new ResourceNotFoundException("Skill Not Found"));
            CertificationModel certificationModel = new CertificationModel();

            certificationModel.setEmitted(LocalDate.now());
            certificationModel.setSkill(skill);
            certificationModel.setUsers(new HashSet<>(Arrays.asList(authUser)));

            certificationModel = certificateRepository.save(certificationModel);

            testRepository.save(testModel);
            return new CertificateDto(certificationModel.getId(), certificationModel.getSkill().getSkillName(), authUser.getFirstName() + " " + authUser.getLastName(), certificationModel.getEmitted());
        } else {
            throw new BusinessException("Not approved in exam.");
        }
    }

    private int getCorrectedAnswers(TestInsertDto testInsertDto) {
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
        return correctAnswers.correctAnswers;
    }

}
