package com.quiz.Controller;

import com.quiz.model.Quiz;
import com.quiz.service.QuizService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    // @CircuitBreaker(name="questionBreaker",fallbackMethod = "questionFallback")
    //Get All Quiz
    @GetMapping()
   // @Retry(name = "quizRetry", fallbackMethod = "questionFallback")
    @RateLimiter(name="quizRateLimiter",fallbackMethod = "questionFallback")
    public List<Quiz> Get(){
        return quizService.get();
    }

    // Correct fallback
    public List<Quiz> questionFallback(Throwable t) {
        System.out.println("Fallback triggered due to: " + t.getMessage());

        Quiz fallbackQuiz = new Quiz();
        fallbackQuiz.setId(0L);
        fallbackQuiz.setTitle("Default Quiz");

        return List.of(fallbackQuiz);
    }


    //Get Quiz By id

    @GetMapping("{id}")
    public Quiz getOne(@PathVariable Long id){
        return quizService.get(id);
    }
}
