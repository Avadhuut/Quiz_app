package com.quiz.service;

import com.quiz.model.Quiz;
import com.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImple implements QuizService{


    private final QuizRepository quizRepository;

    private final QuestionClient questionClient;

    public QuizServiceImple(QuestionClient questionClient, QuizRepository quizRepository) {
        this.questionClient = questionClient;
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {

        List<Quiz>quizzes= quizRepository.findAll();

        List<Quiz>newQuizList=quizzes.stream().map(quiz ->
        {
            quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
            return quiz;
         }).collect(Collectors.toList());

        return newQuizList;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz= quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz Not Found"));

        quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
        return quiz;
    }
}
