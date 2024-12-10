
package com.example.strechingstudio.service;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.model.TrainingRequest;

import java.util.List;


public interface TrainingService {
    List<Training> getUpcomingTrainings();

    List<Training> getPastTrainings();

    void signUpForTraining(Long trainingId, Long userId);

    List<TrainingRequest> getAllTrainingRequests();

    void updateTrainingRequestStatus(Long requestId, boolean isApproved);
}
