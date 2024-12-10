// Сервис для работы с тренировками
package com.example.strechingstudio.service;

import com.example.strechingstudio.model.Training;

import java.util.List;

public interface TrainingService {
    List<Training> getUpcomingTrainings();

    List<Training> getPastTrainings();

    void signUpForTraining(Long trainingId);
}
