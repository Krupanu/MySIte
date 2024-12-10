// Репозиторий для работы с тренировками
package com.example.strechingstudio.repository;

import com.example.strechingstudio.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByIsCompleted(boolean isCompleted);
}
