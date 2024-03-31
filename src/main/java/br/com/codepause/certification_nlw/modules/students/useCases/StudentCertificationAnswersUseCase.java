package br.com.codepause.certification_nlw.modules.students.useCases;

import br.com.codepause.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.codepause.certification_nlw.modules.questions.repository.QuestionRepository;
import br.com.codepause.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.codepause.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import br.com.codepause.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.codepause.certification_nlw.modules.students.entities.StudentEntity;
import br.com.codepause.certification_nlw.modules.students.repository.CertificationStudentEntityRepository;
import br.com.codepause.certification_nlw.modules.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CertificationStudentEntityRepository certificationStudentEntityRepository;

public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto)  {

 //Buscar as alternativas das perguntas
 //correta ou incorreta
    List<QuestionEntity> questionsEntities = questionRepository.findByTechnology(dto.getTechnology());

    List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

    AtomicInteger correctAnswer = new AtomicInteger(0);

    dto.getQuestionsAnswerDTOS().stream().forEach(questionAnswer -> {
        var questions = questionsEntities.stream().
                filter(question -> question.getId().equals(questionAnswer.getQuestionID()))
                .findFirst().get();

        var findCorrectAlternative = questions.getAlternatives().stream().
                filter(alternative -> alternative.isCorrect()).findFirst().get();

        if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
            questionAnswer.setCorrect(true);
            correctAnswer.addAndGet(1);
        }else {
            questionAnswer.setCorrect(false);
        }

       var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                .answerID(questionAnswer.getAlternativeID())
                        .questionID(questionAnswer.getQuestionID())
                                .isCorrect(questionAnswer.isCorrect()).build();

        answersCertifications.add(answersCertificationsEntity);

    });

    //Verificar se existe student pelo email
    var student = studentRepository.findByEmail(dto.getEmail());
    UUID studentID;
    if (student.isEmpty()){
       var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
        studentCreated = studentRepository.save(studentCreated);
        studentID = studentCreated.getId();
    }else {
        studentID = student.get().getId();
    }
    CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                    .technology(dto.getTechnology())
                    .studentID(studentID)
                    .grade(correctAnswer.get())
                    .build();

    var certificationsStudentCreated = certificationStudentEntityRepository.save(certificationStudentEntity);

    answersCertifications.stream().forEach(answerCertification -> {
       answerCertification.setCertificationID(certificationStudentEntity.getId()) ;
       answerCertification.setCertificationStudentEntity(certificationStudentEntity);
    });

    certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

    certificationStudentEntityRepository.save(certificationStudentEntity);

    return certificationsStudentCreated;
 //Salvar as informações da certificação

}

}
