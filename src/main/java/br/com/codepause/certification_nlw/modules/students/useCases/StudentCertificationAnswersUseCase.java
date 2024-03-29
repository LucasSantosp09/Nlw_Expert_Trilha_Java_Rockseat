package br.com.codepause.certification_nlw.modules.students.useCases;

import br.com.codepause.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.codepause.certification_nlw.modules.questions.repository.QuestionRepository;
import br.com.codepause.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.codepause.certification_nlw.modules.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO dto) throws Exception {
 //Verifica se usuário existe
    var student = studentRepository.findByEmail(dto.getEmail());

    if (student.isEmpty()){
        throw new Exception("E-mail do estudante incorreto.");
    }

 //Buscar as alternativas das perguntas
 //correta ou incorreta
    List<QuestionEntity> questionsEntities = questionRepository.findByTechnology(dto.getTechnology());

    dto.getQuestionsAnswerDTOS().stream().forEach(questionAnswer -> {
        var questions = questionsEntities.stream().
                filter(question -> question.getId().equals(questionAnswer.getQuestionID()))
                .findFirst().get();

        var findCorrectAlternative = questions.getAlternatives().stream().
                filter(alternative -> alternative.isCorrect()).findFirst().get();

        if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
            questionAnswer.setCorrect(true);
        }else {
            questionAnswer.setCorrect(false);
        }


    });

    return dto;
 //Salvar as informações da certificação
}

}
