package br.com.codepause.certification_nlw.modules.students.repository;

import br.com.codepause.certification_nlw.modules.students.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository <StudentEntity, UUID> {

    public Optional<StudentEntity>  findByEmail(String email);


}
