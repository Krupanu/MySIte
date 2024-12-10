package com.example.strechingstudio.config;

import com.example.strechingstudio.model.Subscription;
import com.example.strechingstudio.model.Training;
import com.example.strechingstudio.repository.SubscriptionRepository;
import com.example.strechingstudio.repository.TrainingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(SubscriptionRepository subscriptionRepository, TrainingRepository trainingRepository) {
        return args -> {
            // Добавляем подписки
            subscriptionRepository.save(new Subscription(null, "Базовая", "Доступ к 4 тренировкам в месяц", 29.99));
            subscriptionRepository.save(new Subscription(null, "Премиум", "Неограниченные тренировки", 49.99));
            subscriptionRepository.save(new Subscription(null, "VIP", "Персональные тренировки и консультации", 99.99));

            // Добавляем тренировки
            trainingRepository.save(new Training(null, "Растяжка для новичков", LocalDateTime.of(2024, 12, 1, 10, 15), true));
            trainingRepository.save(new Training(null, "Средний уровень: Спина и плечи", LocalDateTime.of(2024, 12, 3, 12, 0), true));
            trainingRepository.save(new Training(null, "Гибкость для танцоров", LocalDateTime.of(2024, 12, 5, 15, 0), true));
            trainingRepository.save(new Training(null, "Расслабление через растяжку", LocalDateTime.of(2024, 12, 8, 18, 30), true));
            trainingRepository.save(new Training(null, "Глубокая растяжка", LocalDateTime.of(2024, 12, 15, 14, 0), false));
        };
    }
}