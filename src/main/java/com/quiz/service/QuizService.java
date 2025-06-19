package com.quiz.service;

import com.quiz.model.Quiz;

import java.util.List;

public interface QuizService {

    Quiz add(Quiz quiz);

    List<Quiz>get();

    Quiz get(Long id);

}
