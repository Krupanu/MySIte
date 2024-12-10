package com.example.strechingstudio.service;

import com.example.strechingstudio.model.TrainingRequest;
import com.example.strechingstudio.model.User;

import java.util.List;

public interface TrainingRequestService {
    TrainingRequest createRequest(Long userId, Long trainingId);
    List<TrainingRequest> getRequestsByUser(Long userId);
    List<TrainingRequest> getAllPendingRequests();
    TrainingRequest updateRequestStatus(Long requestId, String status);
}