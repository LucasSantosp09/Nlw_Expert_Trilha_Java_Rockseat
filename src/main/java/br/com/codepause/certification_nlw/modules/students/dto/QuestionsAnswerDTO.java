package br.com.codepause.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsAnswerDTO {

    private String questionID;
    private String alternativeID;
    private boolean isCorrect;
}
