package br.com.codepause.certification_nlw.modules.students.useCases;

import br.com.codepause.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.codepause.certification_nlw.modules.students.repository.CertificationStudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    CertificationStudentEntityRepository certificationStudentEntityRepository;
    public boolean execute(VerifyHasCertificationDTO dto){
        var result = this.certificationStudentEntityRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if(result.isEmpty()){
            return true;
        }
        return false;
    }
}
