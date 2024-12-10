package com.example.strechingstudio.repository;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.model.TrainingRequest;
import com.example.strechingstudio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRequestRepository extends JpaRepository<TrainingRequest, Long> {
    List<TrainingRequest> findByUser(User user);
    List<TrainingRequest> findByStatus(String status);
    TrainingRequest findByUserAndTraining(User user, Training training);
}
