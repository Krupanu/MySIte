package com.example.strechingstudio.service.impl;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.model.TrainingRequest;
import com.example.strechingstudio.model.User;
import com.example.strechingstudio.repository.TrainingRepository;
import com.example.strechingstudio.repository.TrainingRequestRepository;
import com.example.strechingstudio.repository.UserRepository;
import com.example.strechingstudio.service.TrainingRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingRequestServiceImpl implements TrainingRequestService {

    // Existing Dependencies
    private final TrainingRequestRepository trainingRequestRepository;
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public TrainingRequestServiceImpl(TrainingRequestRepository trainingRequestRepository,
                                      UserRepository userRepository,
                                      TrainingRepository trainingRepository) {
        this.trainingRequestRepository = trainingRequestRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public TrainingRequest createRequest(Long userId, Long trainingId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new IllegalArgumentException("Training not found"));

        TrainingRequest existingRequest = trainingRequestRepository.findByUserAndTraining(user, training);
        if (existingRequest != null) {
            throw new IllegalStateException("Request already exists for this training.");
        }

        TrainingRequest request = new TrainingRequest();
        request.setUser(user);
        request.setTraining(training);
        request.setStatus("PENDING");
        return trainingRequestRepository.save(request);
    }

    @Override
    public List<TrainingRequest> getRequestsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return trainingRequestRepository.findByUser(user);
    }

    @Override
    public List<TrainingRequest> getAllPendingRequests() {
        return trainingRequestRepository.findByStatus("PENDING");
    }

    @Override
    public TrainingRequest updateRequestStatus(Long requestId, String status) {
        // Получаем заявку
        TrainingRequest request = trainingRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Проверяем статус
        if (!List.of("APPROVED", "REJECTED").contains(status)) {
            throw new IllegalArgumentException("Invalid status.");
        }

        // Обновляем статус и сохраняем
        request.setStatus(status);
        return trainingRequestRepository.save(request);
    }

}
