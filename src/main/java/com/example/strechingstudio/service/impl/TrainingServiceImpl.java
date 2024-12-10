// Реализация сервиса
package com.example.strechingstudio.service.impl;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.repository.TrainingRepository;
import com.example.strechingstudio.service.TrainingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> getUpcomingTrainings() {
        return trainingRepository.findByIsCompleted(false);
    }

    @Override
    public List<Training> getPastTrainings() {
        return trainingRepository.findByIsCompleted(true);
    }

    @Override
    public void signUpForTraining(Long trainingId) {
        // Реализация записи на тренировку
    }
}
