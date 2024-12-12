package com.example.strechingstudio.controller;

import com.example.strechingstudio.model.TrainingRequest;
import com.example.strechingstudio.service.TrainingRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class TrainingRequestController {

    private final TrainingRequestService trainingRequestService;

    public TrainingRequestController(TrainingRequestService trainingRequestService) {
        this.trainingRequestService = trainingRequestService;
    }

    @PostMapping("/create")
    public String createRequest(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long trainingId) {
        Long userId = getUserIdFromDetails(userDetails);
        trainingRequestService.createRequest(userId, trainingId);
        return "redirect:/trainings";
    }

    @GetMapping("/user")
    public String getUserRequests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long userId = getUserIdFromDetails(userDetails);
        List<TrainingRequest> requests = trainingRequestService.getRequestsByUser(userId);
        model.addAttribute("requests", requests);
        return "user_requests";
    }

    @GetMapping("/pending")
    public String getPendingRequests(Model model) {
        List<TrainingRequest> requests = trainingRequestService.getAllPendingRequests();
        model.addAttribute("pendingRequests", requests);
        return "admin_requests";
    }

    @PostMapping("/update")
    public String updateRequestStatus(@RequestParam Long requestId, @RequestParam String status, Model model) {
        // Проверяем статус на допустимые значения
        if (!List.of("APPROVED", "REJECTED").contains(status.toUpperCase())) {
            model.addAttribute("error", "Недопустимый статус.");
            return "redirect:/requests/pending?error";
        }

        try {
            trainingRequestService.updateRequestStatus(requestId, status.toUpperCase());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/requests/pending?error";
        }

        return "redirect:/requests/pending?success";
    }

    @PostMapping("/cancel")
    public String cancelRequest(@RequestParam Long requestId, Model model) {
        try {
            trainingRequestService.cancelRequest(requestId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/requests/user?error";
        }
        return "redirect:/requests/user?success";
    }

    private Long getUserIdFromDetails(UserDetails userDetails) {
        // Implement logic to fetch user ID from UserDetails
        return 1L;
    }
}
