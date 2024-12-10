package com.example.strechingstudio.controller;

import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/trainings")
    public String showTrainings(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        // Форматируем предстоящие тренировки
        List<Training> upcomingTrainings = trainingService.getUpcomingTrainings().stream()
                .map(training -> {
                    training.setFormattedDateTime(training.getDateTime().format(formatter));
                    return training;
                }).collect(Collectors.toList());

        // Форматируем прошедшие тренировки
        List<Training> pastTrainings = trainingService.getPastTrainings().stream()
                .map(training -> {
                    training.setFormattedDateTime(training.getDateTime().format(formatter));
                    return training;
                }).collect(Collectors.toList());


        model.addAttribute("upcomingTrainings", trainingService.getUpcomingTrainings());
        model.addAttribute("pastTrainings", trainingService.getPastTrainings());
        return "trainings";
    }
}