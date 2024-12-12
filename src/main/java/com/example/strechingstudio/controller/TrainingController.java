package com.example.strechingstudio.controller;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.service.TrainingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping

    public String showTrainings(Model model) {
        model.addAttribute("upcomingTrainings", trainingService.getUpcomingTrainings());
        model.addAttribute("pastTrainings", trainingService.getPastTrainings());
        return "trainings";
    }


    @GetMapping("/add")
    public String showAddTrainingForm(Model model) {
        model.addAttribute("training", new Training());
        return "add_training";
    }

    @PostMapping("/add")
    public String addTraining(Training training) {
        trainingService.saveTraining(training);
        return "redirect:/trainings";
    }
}