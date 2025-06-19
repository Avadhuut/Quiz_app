package com.quiz.Controller;

import com.quiz.model.Quiz;
import com.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizCotroller {

    private QuizService quizService;


    public QuizCotroller(QuizService quizService) {
        this.quizService = quizService;
    }


    //create Quiz
    @PostMapping()
    public Quiz Create(@RequestBody Quiz quiz){
        return quizService.add(quiz);
    }

    //Get All Quiz
    @GetMapping()
    public List<Quiz> Get(){
        return quizService.get();
    }


    //Get Quiz By id

    @GetMapping("{id}")
    public Quiz getOne(@PathVariable Long id){
        return quizService.get(id);
    }
}
