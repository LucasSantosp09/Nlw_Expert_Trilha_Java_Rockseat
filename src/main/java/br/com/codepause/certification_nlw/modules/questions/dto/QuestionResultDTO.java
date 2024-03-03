package br.com.codepause.certification_nlw.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultDTO {

    private UUID id;
    private String technologu;
    private String description;

    private List<AlternativesResultDTO> alternatives;

}
