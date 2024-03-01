package br.com.codepause.certification_nlw.modules.students.repository;

import br.com.codepause.certification_nlw.modules.students.entities.CertificationStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface CertificationStudentEntityRepository  extends JpaRepository<CertificationStudentEntity, UUID> {


}
