// Контроллер для отображения тренировок и подписок
package com.example.strechingstudio.controller;

import com.example.strechingstudio.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/trainings")
    public String showTrainings(Model model) {
        model.addAttribute("upcomingTrainings", trainingService.getUpcomingTrainings());
        model.addAttribute("pastTrainings", trainingService.getPastTrainings());
        return "trainings";
    }
}
