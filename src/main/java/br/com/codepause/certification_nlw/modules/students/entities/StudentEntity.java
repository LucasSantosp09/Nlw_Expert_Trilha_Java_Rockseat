package br.com.codepause.certification_nlw.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (unique = true, nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "studentEntity")
    private List<CertificationStudentEntity> certificationStudentEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
