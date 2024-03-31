package br.com.codepause.certification_nlw.modules.certifications.useCases;

import br.com.codepause.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.codepause.certification_nlw.modules.students.repository.CertificationStudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentEntityRepository certificationStudentEntityRepository;

    public List<CertificationStudentEntity> execute(){
       return this.certificationStudentEntityRepository.findTop10ByOrderByGradeDesc();
    }

}
