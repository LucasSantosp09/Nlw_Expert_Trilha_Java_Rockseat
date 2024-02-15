package br.com.codepause.certification_nlw.modules.students.controllers;

import br.com.codepause.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping("/verifyHasCertification")
    public String verifyHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO){
        System.out.println(verifyHasCertificationDTO);
        return "Usuário pode fazer a prova";
    }


}
