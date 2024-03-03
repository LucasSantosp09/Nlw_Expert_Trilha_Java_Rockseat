package br.com.codepause.certification_nlw.modules.questions.controllers;

import br.com.codepause.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.codepause.certification_nlw.modules.questions.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionEntity> findByTechnology(@PathVariable String technology){
        return this.questionRepository.findByTechnology(technology);
    }

}
