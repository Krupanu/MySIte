package com.example.strechingstudio.service.impl;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.model.TrainingRequest;
import com.example.strechingstudio.repository.TrainingRepository;
import com.example.strechingstudio.service.TrainingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> getUpcomingTrainings() {
        List<Training> trainings = trainingRepository.findByIsCompleted(false);
        if (trainings == null || trainings.isEmpty()) {
            System.out.println("No upcoming trainings found.");
        }
        return trainings;
    }

    @Override
    public List<Training> getPastTrainings() {
        List<Training> trainings = trainingRepository.findByIsCompleted(true);
        if (trainings == null || trainings.isEmpty()) {
            System.out.println("No past trainings found.");
        }
        return trainings;
    }


    @Override
    public void signUpForTraining(Long trainingId, Long userId) {

    }

    @Override
    public List<TrainingRequest> getAllTrainingRequests() {
        return List.of();
    }

    @Override
    public void saveTraining(Training training) {
        training.setDateTime(LocalDateTime.now());
        trainingRepository.save(training);
    }

}
