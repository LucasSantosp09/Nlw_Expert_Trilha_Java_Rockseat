package br.com.codepause.certification_nlw.modules.students.controllers;

import br.com.codepause.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.codepause.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.codepause.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.codepause.certification_nlw.modules.students.repository.CertificationStudentEntityRepository;
import br.com.codepause.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import br.com.codepause.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyHasCertification")
    public String verifyHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO){
        var result = verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
        if(result){
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public StudentCertificationAnswerDTO certificationAnswer (@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) throws  Exception{
       return this.studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
    }

}
